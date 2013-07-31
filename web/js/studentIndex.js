function getFileList(isDir, groupName) {
    $.post("getFilelist.action", {
        isDir: isDir,
        groupName : groupName
    }, function(data) {
        $('#filelist').html(data);
    });
}

function getPublicFilelist(currentPage, name){
    $.post("getPublicFilelist.action", {
        currentPage: currentPage,
        name: name
    }, function(data) {
       $("#publicFileList").html(data); 
    });
}

function getBillList(currentPage){
    $.post("getBillList.action", {currentPage: currentPage}, function(data){
       $("#bill_area").html(data); 
    });
}

function getCommentBillList(currentPage){
    $.post("getCommentBill.action", {currentPage: currentPage}, function(data){
       $("#comment_area").html(data); 
    });
}

jQuery(document).ready(function($){
    
    $("[id*='dealApply_stop_button_']").click(function(){
        var id = this.id;
        id = id.substring(22, id.length);
        $("#dealApply_stop_"+id).submit();
    });
    
    getFileList(true, null);
    getPublicFilelist(1);
    getBillList(1);
    getCommentBillList(1);
});