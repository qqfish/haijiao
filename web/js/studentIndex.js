function getFileList(isDir, groupName) {
    $.post("getFilelist.action", {
        isDir: isDir,
        groupName: groupName
    }, function(data) {
        $('#filelist').html(data);
    });
}

function getPublicFilelist(currentPage, name) {
    $.post("getPublicFilelist.action", {
        currentPage: currentPage,
        name: name
    }, function(data) {
        $("#publicFileList").html(data);
    });
}

function getBillList(currentPage) {
    $.post("getBillList.action", {
        currentPage: currentPage,
        isIndex: true,
        status: -1
    }, function(data) {
        $("#billlist").html(data);
    });
}

function getCommentBillList(currentPage) {
    $.post("getCommentBill.action", {
        currentPage: currentPage,
        isIndex: true
    }, function(data) {
        $("#comment_area").html(data);
    });
}

jQuery(document).ready(function($) {

    $("[id*='dealApply_stop_button_']").click(function() {
        var id = this.id;
        id = id.substring(22, id.length);
        $("#dealApply_stop_" + id).submit();
    });

    getFileList(true, null);
    getPublicFilelist(1);
    getBillList(1);
    getCommentBillList(1);
    
    var lessons = [
    'Сѧ����','Сѧ��ѧ','СѧӢ��','Сѧ����','��һ��������','��һ������ѧ','��һ����Ӣ��','��������','������ѧ',
    '����Ӣ��','��������','���л�ѧ','������ʷ','���е���','��һ�߶�����','��һ�߶���ѧ','��һ�߶�Ӣ��','��������',
    '������ѧ','����Ӣ��','��������','���л�ѧ','��������','������ʷ','��������','���е���','�߿�������ѯ','��ѧ�ļ�',
    '��ѧ����','�и�','GRE','��˼','����','����Ӣ��','������ѧ','��������','����רҵ��'
    ];
    $("#lessonSelect").autocomplete(lessons,{
        minChars: 0,
        max: 5,
        autoFill: true,
        matchContains: true,
        scrollHeight: 220,
        multiple: true,
        formatItem: function(data, i, total) {
            return "<I>"+data[0]+"</I>";
        },
        formatMatch: function(data, i, total) {
            return data[0];
        },
        formatResult: function(data) {
            return data[0];
        }
    });
});