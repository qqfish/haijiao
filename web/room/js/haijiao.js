jQuery(document).ready(function($){
 
 
 
$('.close-sidebar').click(function() {  //点击class=“close-sidebar”的对象,即导航中“关闭侧边栏”时
 
  // $('.sidebar').hide();       //隐藏class=“close-sidebar”和“sidebar”的对象,即导航中“关闭侧边栏”和主题的“侧边栏”
 
   //$('.show-sidebar').show();      //显示class=“show-sidebar”的对象,即导航中“显示侧边栏”
 
   $('#side').animate({marginLeft: "-250px"}, 1000); 
   }); //以1000毫秒让id=“content”的对象,即“文章主体部分”的

/*$('.show-sidebar').click(function() {  //点击导航中“显示侧边栏”时
 
   $('.show-sidebar').hide();             //隐藏导航中“显示侧边栏”
 
   $('.close-sidebar,.sidebar').show();        //显示导航中“关闭侧边栏”和主题的“侧边栏”
 
   $('#side').animate({width: "0px"}, 1000);});    //以1000毫秒让“文章主体部分”的宽度收缩回705px
 */
 
 $('.favor-close-sidebar').click(function() {  //点击class=“close-sidebar”的对象,即导航中“关闭侧边栏”时
 
   //$('.favor-close-sidebar,.sidebar').hide();       //隐藏class=“close-sidebar”和“sidebar”的对象,即导航中“关闭侧边栏”和主题的“侧边栏”
 
   $('.favor-show-sidebar').show();      //显示class=“show-sidebar”的对象,即导航中“显示侧边栏”
 
   $('#user-content').hide();
 
   $('#file-content').hide();
   
   $('#side').animate({marginLeft: "-250px"}, 1000); 
  
   $('#side').animate({marginLeft: "0px"}, 1000); //以1000毫秒让“文章主体部分”的宽度收缩回705px
  
   $('#favor-content').show();}); //以1000毫秒让id=“content”的对象,即“文章主体部分”的宽度增加到960px
 
/*$('.favor-show-sidebar').click(function() {  //点击导航中“显示侧边栏”时
 
   $('.favor-show-sidebar').hide();             //隐藏导航中“显示侧边栏”
 
   $('.favor-close-sidebar,.sidebar').show();        //显示导航中“关闭侧边栏”和主题的“侧边栏”
 
   $('#side').animate({width: "0px"}, 1000); //以1000毫秒让“文章主体部分”的宽度收缩回705px
   
   $('#favor-content').hide();});   */
   
   $('.user-close-sidebar').click(function() {  //点击class=“close-sidebar”的对象,即导航中“关闭侧边栏”时
 
   //$('.user-close-sidebar,.sidebar').hide();       //隐藏class=“close-sidebar”和“sidebar”的对象,即导航中“关闭侧边栏”和主题的“侧边栏”
 
   $('.user-show-sidebar').show();      //显示class=“show-sidebar”的对象,即导航中“显示侧边栏”
 
   $('#favor-content').hide();
 
   $('#file-content').hide();
   
   $('#side').animate({marginLeft: "-250px"}, 1000); 
  
   $('#side').animate({marginLeft: "0px"}, 1000);
  
   $('#user-content').show();}); //以1000毫秒让id=“content”的对象,即“文章主体部分”的宽度增加到960px
 
/*$('.user-show-sidebar').click(function() {  //点击导航中“显示侧边栏”时
 
   $('.user-show-sidebar').hide();             //隐藏导航中“显示侧边栏”
 
   $('.user-close-sidebar,.sidebar').show();        //显示导航中“关闭侧边栏”和主题的“侧边栏”
 
   $('#side').animate({width: "0px"}, 1000); //以1000毫秒让“文章主体部分”的宽度收缩回705px
   
   $('#user-content').hide();});   */
   
   $('.file-close-sidebar').click(function() {  //点击class=“close-sidebar”的对象,即导航中“关闭侧边栏”时
 
   //$('.file-close-sidebar,.sidebar').hide();       //隐藏class=“close-sidebar”和“sidebar”的对象,即导航中“关闭侧边栏”和主题的“侧边栏”
 
   $('.file-show-sidebar').show();      //显示class=“show-sidebar”的对象,即导航中“显示侧边栏”
 
   $('#favor-content').hide();
 
   $('#user-content').hide(); 
 
   $('#side').animate({marginLeft: "-250px"}, 1000); 
  
   $('#side').animate({marginLeft: "0px"}, 1000);  
  
   $('#file-content').show();}); //以1000毫秒让id=“content”的对象,即“文章主体部分”的宽度增加到960px
 
/*$('.file-show-sidebar').click(function() {  //点击导航中“显示侧边栏”时
 
   $('.file-show-sidebar').hide();             //隐藏导航中“显示侧边栏”
 
   $('.file-close-sidebar,.sidebar').show();        //显示导航中“关闭侧边栏”和主题的“侧边栏”
 
   $('#side').animate({width: "0px"}, 1000); //以1000毫秒让“文章主体部分”的宽度收缩回705px
   
   $('#file-content').hide();});*/
   
   
});