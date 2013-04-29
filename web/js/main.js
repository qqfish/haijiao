 $(document).ready(function () {
 
     // 使超链接支持click事件，方便JavaScript调用
     $('a.forward').click(function () {
         location.href = $(this)[0].href;
         return false;
     });
 
 });