/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    $('.schedule_table').find('td').mouseover(function() {
        if ($(this).attr("marked") != 1) {
            $(this).css("background-color", "#CFC");
            $(this).css("border", "1px solid #CCC");
        }

    });
    $('.schedule_table').find('td').mouseout(function() {
        $(this).css("border", "0px solid #CCC");
        if ($(this).attr("marked") != 1) {
            $(this).removeAttr("style");
        }

    });
    $('.schedule_table').find('td').click(function() {
        if ($(this).attr("marked") != 1) {
            $(this).css("background-color", "#FC9");
            $(this).attr("marked","1");
        } else {
            $(this).removeAttr("style");
            $(this).removeAttr("marked");
        }
    });
    
});