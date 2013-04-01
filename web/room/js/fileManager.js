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
    
    this.scan = function(node){
        this.title = node.title;
        this.page = node.page;
        this.element = $("<li></li>").attr("class","sideBarButton");
        var link = $("<span></span>").text(node.title).attr("page",node.page);
        if(node.children.length > 0){
            var childDiv = $("<ul></ul>").attr("title",node.title);
            for(var i = 0; i < node.children.length; i++){
                var child = new Bookmark(uuid);
                child.scan(node.children[i]);
                childDiv.append(child.element);
                this.children[this.children.length] = child;
            }
            var show = $("<IMG></IMG>").attr("src","image/jiantou.png").attr("status","-");
            show.rotate(90);
            this.element.append(show);
            show.click(function(){
                if($(this).attr("status") == "+"){
                    $(this).rotate({
                        animateTo:90
                    });
                    $(this).attr("status","-");
                } else {
                    $(this).rotate({
                        animateTo:0
                    });
                    $(this).attr("status","+");
                }
                childDiv.slideToggle();
            });
            this.element.append(link);
            this.element.append(childDiv);
            childDiv.slideToggle();
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
    }
    
    this.addRoomFile = function(response){
        for(var i = 0; i < response.fileList.length; i++){
            var file = new roomFile();
            var r = response.fileList[i];
            file.fileName = r.fileName;
            file.uuid = r.uuid;
            file.element = $("<li></li>").attr("class","sideBarButton").text(file.fileName).attr("uuid",file.uuid);
            roomFileDiv.append(file.element);
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
    
    this.uploadFile = function(file){
        var blob = null;
        blob = sliceFile(file);
        var reader = new FileReader();
        var pos = file.name.lastIndexOf(".");
        var type = file.name.substring(pos+1, file.name.length);
        if(file.type.indexOf('image') != -1){
            type = "image";
            reader.readAsDataURL(blob);
            reader.onstart = function(e){
                lock();
            }
            reader.onload = function loaded(evt) {
                var imgObj = new Image();
                imgObj.onload = function(){
                    table.uploadImage(this);
                };
                imgObj.src = evt.target.result;
            }
        } else if(type == "pdf"){
            reader.readAsBinaryString(blob);
            reader.onstart = function(e){
                lock();
            }
            reader.onload = function loaded(evt) {
                var message = {};
                message.type = Request.UploadFile;
                message.postfix = type;
                message.data = evt.target.result;
                connection.sendObject(message);
            }
        }
    }
}