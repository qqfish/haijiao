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
    this.childDiv = null;
    this.children = new Array();
    
    this.scan = function(node){
        this.title = node.title;
        this.page = node.page;
        this.element = $("<button></button>").text(node.title).attr("page",node.page);
        this.childDiv = $("<div></div>").attr("title",node.title);
        
        for(var i = 0; i < node.children.length; i++){
            var child = new Bookmark(uuid);
            child.scan(node.children[i]);
            this.childDiv.append(child.element);
            this.childDiv.append(child.childDiv);
            this.children[this.children.length] = child;
        }
        
        this.element.click(function(){
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
            child.scan(response.bookmarkList[i]);
            bookmardDiv.append(child.element);
            bookmardDiv.append(child.childDiv);
            bookmarks[bookmarks.length] = child;
        }
    }
    
    this.addRoomFile = function(response){
        for(var i = 0; i < response.fileList.length; i++){
            var file = new roomFile();
            var r = response.fileList[i];
            file.fileName = r.fileName;
            file.uuid = r.uuid;
            file.element = $("<button></button>").text(file.fileName).attr("uuid",file.uuid);
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
            var groupDiv = $("<div></div>").attr("group",group.groupName);
            userFileDiv.append(groupDiv);
            for(var j = 0; j < group.files.length; j++){
                var file = $("<button></button>").text(group.files[j].name).attr("name",group.files[i].name).attr("group",group.groupName);
                file.click(function(){
                    addFileFromUser($(this).attr("group"),$(this).attr("name"));
                });
                userFileDiv.append(file);
            }
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
}