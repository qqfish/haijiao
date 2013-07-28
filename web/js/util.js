/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function checkPositiveNumber(obj){
    var val = obj.val();
    if(isNaN(val) || val < 0)
        obj.val(0);
}

function checkNatureNumber(obj){
    var val = obj.val();
    if(isNaN(val) || val <= 0)
        obj.val(1);
}