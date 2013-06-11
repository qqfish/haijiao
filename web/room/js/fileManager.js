/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function roomFile(){
    this.fileName = null;
    this.uuid = null;
    this.element = null;
}

function Bookmark(uuid){
    this.title = null;
    this.page = null;
    this.element = null;
    this.children = new Array();
    
//    this.scan = function(node){
//        this.title = node.title;
//        this.page = node.page;
//        this.element = $("<li></li>").attr("class","sideBarButton");
//        var link = $("<span></span>").text(node.title).attr("page",node.page);
//        if(node.children.length > 0){
//            var childDiv = $("<ul></ul>").attr("title",node.title);
//            for(var i = 0; i < node.children.length; i++){
//                var child = new Bookmark(uuid);
//                child.scan(node.children[i]);
//                childDiv.append(child.element);
//                this.children[this.children.length] = child;
//            }
//            var show = $("<IMG></IMG>").attr("src","image/jiantou.png").attr("status","-");
//            show.rotate(90);
//            this.element.append(show);
//            show.click(function(){
//                if($(this).attr("status") == "+"){
//                    $(this).rotate({
//                        animateTo:90
//                    });
//                    $(this).attr("status","-");
//                } else {
//                    $(this).rotate({
//                        animateTo:0
//                    });
//                    $(this).attr("status","+");
//                }
//                childDiv.slideToggle();
//            });
//            this.element.append(link);
//            this.element.append(childDiv);
//            childDiv.slideToggle();
//        } else {
//            this.element.append(link);
//        }
//        link.click(function(){
//            table.sendChangePage(uuid, $(this).attr("page"));
//        });
//    }
    
    this.scan = function(node){
        this.title = node.title;
        this.page = node.page;
        this.element = $("<li></li>");
        var link = $("<a></a>").text(node.title).attr("page",node.page).attr("tabindex","-1");
        if(node.children.length > 0){
            var childDiv = $("<ul></ul>").attr("class","dropdown-menu");
            for(var i = 0; i < node.children.length; i++){
                var child = new Bookmark(uuid);
                child.scan(node.children[i]);
                childDiv.append(child.element);
                this.children[this.children.length] = child;
            }
            this.element.attr("class","dropdown-submenu");
            this.element.append(link);
            this.element.append(childDiv);
        } else {
            this.element.append(link);
        }
        link.click(function(){
            table.sendChangePage(uuid, $(this).attr("page"));
        });
    }
}


function fileManager(dRoomFile, dBookmark, dUserFile){
    var currentUuid = null;
    var pageNum = 0;
    
    var roomFileList = new Array();
    var roomFileDiv = $("#" + dRoomFile);
    
    var bookmarks = new Array();
    var bookmardDiv = $("#" + dBookmark);
    
    var userFileDiv = $("#" + dUserFile);
    
    this.setPage = function(response){
        currentUuid = response.uuid;
        pageNum = response.page;
    }
    
    this.changeBookmark = function(response){
        bookmardDiv.empty();
        
        bookmarks = null;
        bookmarks = new Array();
        for(var i = 0; i < response.bookmarkList.length; i++){
            var child = new Bookmark(currentUuid);
            child.scan(response.bookmarkList[i],"");
            bookmardDiv.append(child.element);
            bookmarks[bookmarks.length] = child;
        }
        if(bookmarks.length == 0){
            var empty = $("<li></li>");
            empty.html("<a tabindex='-1'>该文档没有书签</a>").attr("class","disabled");
            bookmardDiv.append(empty);
        }
    }
    
    this.addRoomFile = function(response){
        for(var i = 0; i < response.fileList.length; i++){
            var file = new roomFile();
            var r = response.fileList[i];
            file.fileName = r.fileName;
            file.uuid = r.uuid;
            file.element = $("<li></li>").html("<a tabindex='-10'>" + file.fileName + "</a>").attr("uuid",file.uuid);
            roomFileDiv.prepend(file.element);
            file.element.click(function(){
                table.sendChangeFile($(this).attr("uuid"));
            });
            roomFileList[roomFileList.size] = file;
        }
    }
    
    this.setUserFile = function(response){
        for(var i = 0; i < response.groupList.length; i++){
            var group = response.groupList[i];
            var groupLi = $("<li></li>").attr("class","slideBarButton");
            var groupUl = $("<ul></ul").attr("group",group.groupName);
            var groupSpan = $("<span></span>").text(group.groupName);
            groupLi.append(groupSpan);
            groupSpan.click(function(){
                groupUl.slideToggle();
            });
            groupLi.append(groupUl);
            for(var j = 0; j < group.files.length; j++){
                var file = $("<li></li>").text(group.files[j].name).attr("class","sideBarButton").attr("name",group.files[i].name).attr("group",group.groupName);
                file.click(function(){
                    addFileFromUser($(this).attr("group"),$(this).attr("name"));
                });
                groupUl.append(file);
            }
            userFileDiv.append(groupLi);
        }
    }
    
    function addFileFromUser(group, name){
        var message = {};
        message.type = Request.AddFileFromUser;
        message.group = group;
        message.name = name;
        
        connection.sendObject(message);
    }
    
    this.nextPage = function(){
        table.sendChangePage(currentUuid, pageNum + 1);
    }
    
    this.prePage = function(){
        table.sendChangePage(currentUuid, pageNum - 1);
    }
    
    this.gotoPage = function(page){
        table.sendChangePage(currentUuid, page - 1);
    }
    
    this.downloadFile = function(){
        table.sendDownload();
    }
    
    this.downloadResponse = function(path){
        $("#downloadPath").val(path);
        $("#downlaodForm").submit();
        console.log($("#downloadPath").val());
    }
    
    this.uploadFile = function(file){
        var blob = null;
        blob = sliceFile(file);
        var reader = new FileReader();
        var pos = file.name.lastIndexOf(".");
        var type = file.name.substring(pos+1, file.name.length);
        if(file.type.indexOf('image') != -1){
            type = "image";
            reader.readAsDataURL(blob);
            theBar = $("<div></div>").attr("class","bar");
            progressBar = $("<div></div>").attr("class","progress").append(theBar);
            progressWord = $("<h3></h3>").text("上传中");
            $('#alertContext').empty().append(progressWord).append(progressBar);
            theBar.css("width","0");
            lockType("info");
            lock();
            reader.onprogress = function updateProgress(evt){
                if (evt.lengthComputable){
                    var percentLoaded = Math.round((evt.loaded / evt.total) * 100);
                    if (percentLoaded < 100) {
                        theBar.css("width",percentLoaded+"%");
                    }
                }
            }
            reader.onerror = function (evt) {
                progressWord = $("<h3></h3>").text("上传失败");
                lockType("error");
                $("#closeAlert").show();
            }
            reader.onload = function loaded(evt) {
                var imgObj = new Image();
                imgObj.onload = function(){
                    table.uploadImage(this);
                };
                imgObj.src = evt.target.result;
                progressWord.text("上传成功(移动图片位置，双击图片确认)")
                theBar.css("width","100%");
                $("#closeAlert").show();
            }
        } else if(type == "pdf"){
            reader.readAsDataURL(blob);
            theBar = $("<div></div>").attr("class","bar");
            progressBar = $("<div></div>").attr("class","progress").append(theBar);
            progressWord = $("<h3></h3>").text("上传中");
            $('#alertContext').empty().append(progressWord).append(progressBar);
            theBar.css("width","0");
            lockType("info");
            lock();
            reader.onprogress = function updateProgress(evt){
                if (evt.lengthComputable){
                    var percentLoaded = Math.round((evt.loaded / evt.total) * 100);
                    if (percentLoaded < 100) {
                        theBar.css("width",percentLoaded+"%");
                    }
                }
            }
            reader.onerror = function (evt) {
                progressWord = $("<h3></h3>").text("上传失败");
                lockType("error");
                $("#closeAlert").show();
            }
            reader.onload = function loaded(evt) {
                var message = {};
                message.type = Request.UploadFile;
                message.postfix = type;
                message.name = file.name;
                message.data = evt.target.result;
                console.log("befor send");
                connection.sendObject(message);
                progressWord.text("上传成功(由文件列表中打开上传文件)");
                theBar.css("width","100%");
                $("#closeAlert").show();
            }
        } else {
            pError("该格式的文档尚未支持(目前支持图片和pdf)")
        }
    }
}