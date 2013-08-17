/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getBillList(currentPage) {
    $.post("getBillList.action", {
        currentPage: currentPage,
        isIndex: false,
        status: 5,
        email: $('#teaemail').val()
    }, function(data) {
        $("#bill_area").html(data);
    });
}

function getCommentBillList(currentPage) {
    $.post("getCommentBill.action", {
        currentPage: currentPage,
        isIndex: false,
        email: $('#teaemail').val()
    }, function(data) {
        $("#comment_area").html(data);
    });
}

jQuery(document).ready(function() {
    getBillList(1);
    getCommentBillList(1);
});