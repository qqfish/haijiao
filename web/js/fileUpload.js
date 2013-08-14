/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function testUpload(fileid) {
    var maxLength = 10*1024*1024;
    var src = $("#" + fileid).val();
    var extStart = src.lastIndexOf('.');
    var ext = src.substr(extStart,src.length).toUpperCase();
    if (ext!=".PDF"&&ext!=".ODT"&&ext!=".SXW"&&ext!=".RTF"&&ext!=".DOC"&&ext!=".DOCX"&&ext!=".WPD"&&ext!=".TXT"&&ext!=".ODS"&&ext!=".SXC"&&ext!=".XLS"&&ext!=".XLSX"&&ext!=".CSV"&&ext!=".TSV"&&ext!=".ODP"&&ext!=".SXI"&&ext!=".PPT"&&ext!=".PPTX"&&ext!=".ODG") {
        $("#upload_tip").text("* 支持格式pdf,odt,sxw,rtf,doc,docx,wpd,txt,ods,sxc,xls,xlsx,csv,tsv,odp,sxi,ppt,pptx,odg");
        $("#upload_tip").fadeIn(1,null);
        return;
    }
    if(document.getElementById(fileid).files[0].size > maxLength){
        $("#upload_tip").text("* 文件过大，最多10M");
        $("#upload_tip").fadeIn(1,null);
        return;
    }
    $('#fileUpload').submit();
}