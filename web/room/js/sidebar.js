jQuery(document).ready(function($){
 
 
 
    var sideShow = false;
    function sideReturn(){
        $('#side').animate({
            marginLeft: "-250px"
        }, 'fast');
        
        $('#desktop').animate({
            left: "0",
            width: $(window).width()
        },'fast');
        media.setDragPlace(0, 52, $(window).width()-media.getWidth(), $(window).height()-media.getHeight());
        
        $("#desktop").unbind("click");
        
        sideShow = false;
    }
    $('.close-sidebar').click(function(){
        sideReturn();
    }); //以1000毫秒让id=“content”的对象,即“文章主体部分”的

    /*$('.show-sidebar').click(function() {  //点击导航中“显示侧边栏”时
 
   $('.show-sidebar').hide();             //隐藏导航中“显示侧边栏”
 
   $('.close-sidebar,.sidebar').show();        //显示导航中“关闭侧边栏”和主题的“侧边栏”
 
   $('#side').animate({width: "0px"}, 1000);});    //以1000毫秒让“文章主体部分”的宽度收缩回705px
 */
 
    $('.favor-close-sidebar').click(function() {  //点击class=“close-sidebar”的对象,即导航中“关闭侧边栏”时
 
        //$('.favor-close-sidebar,.sidebar').hide();       //隐藏class=“close-sidebar”和“sidebar”的对象,即导航中“关闭侧边栏”和主题的“侧边栏”

        if(sideShow){
            $('#side').animate({
                marginLeft: "-250px"
            }, 'fast', function(){
                $('#favor-content').show();
            
                $('#user-content').hide();
 
                $('#file-content').hide();
        
                $('#userFile-content').hide();
            });
        
            $('#desktop').animate({
                left: "0",
                width: $(window).width()
            },'fast');
        } else {
            $('#favor-content').show();
            
            $('#user-content').hide();
 
            $('#file-content').hide();
        
            $('#userFile-content').hide();
        }
        
        $('#desktop').animate({
            left: "250px",
            width: '-=250px'
        },'fast');
  
        $('#side').animate({
            marginLeft: "0px"
        }, 'fast'); //以1000毫秒让“文章主体部分”的宽度收缩回705px
        
        $("#desktop").click(function(){
            sideReturn();
        });
        
        media.setDragPlace(250, 52, $(window).width()-media.getWidth(), $(window).height()-media.getHeight());
        sideShow = true;
    }); //以1000毫秒让id=“content”的对象,即“文章主体部分”的宽度增加到960px
 
    /*$('.favor-show-sidebar').click(function() {  //点击导航中“显示侧边栏”时
 
   $('.favor-show-sidebar').hide();             //隐藏导航中“显示侧边栏”
 
   $('.favor-close-sidebar,.sidebar').show();        //显示导航中“关闭侧边栏”和主题的“侧边栏”
 
   $('#side').animate({width: "0px"}, 1000); //以1000毫秒让“文章主体部分”的宽度收缩回705px
   
   $('#favor-content').hide();});   */
 
    /*$('.user-show-sidebar').click(function() {  //点击导航中“显示侧边栏”时
 
   $('.user-show-sidebar').hide();             //隐藏导航中“显示侧边栏”
 
   $('.user-close-sidebar,.sidebar').show();        //显示导航中“关闭侧边栏”和主题的“侧边栏”
 
   $('#side').animate({width: "0px"}, 1000); //以1000毫秒让“文章主体部分”的宽度收缩回705px
   
   $('#user-content').hide();});   */
   
    /*$('.file-close-sidebar').click(function() {  //点击class=“close-sidebar”的对象,即导航中“关闭侧边栏”时
 
        //$('.file-close-sidebar,.sidebar').hide();       //隐藏class=“close-sidebar”和“sidebar”的对象,即导航中“关闭侧边栏”和主题的“侧边栏”

        if(sideShow){
            $('#side').animate({
                marginLeft: "-250px"
            }, 'fast', function(){
                $('#file-content').show();
            
                $('#favor-content').hide();
 
                $('#user-content').hide(); 
        
                $('#userFile-content').hide();
            });
        
            $('#desktop').animate({
                left: "0",
                width: $(window).width()
            },'fast');
        } else {
            $('#file-content').show();
            
            $('#favor-content').hide();
 
            $('#user-content').hide(); 
        
            $('#userFile-content').hide();
        }
        
        $('#desktop').animate({
            left: "250px",
            width: '-=250px'
        },'fast');
  
        $('#side').animate({
            marginLeft: "0px"
        }, 'fast');  
          
        $("#desktop").click(function(){
            sideReturn();
        });
        media.setDragPlace(250, 52, $(window).width()-media.getWidth(), $(window).height()-media.getHeight());

        sideShow = true;

    }); //以1000毫秒让id=“content”的对象,即“文章主体部分”的宽度增加到960px */
 
    /*$('.file-show-sidebar').click(function() {  //点击导航中“显示侧边栏”时
 
   $('.file-show-sidebar').hide();             //隐藏导航中“显示侧边栏”
 
   $('.file-close-sidebar,.sidebar').show();        //显示导航中“关闭侧边栏”和主题的“侧边栏”
 
   $('#side').animate({width: "0px"}, 1000); //以1000毫秒让“文章主体部分”的宽度收缩回705px
   
   $('#file-content').hide();});*/
   
    $('.userFile-close-sidebar').click(function() {  //点击class=“close-sidebar”的对象,即导航中“关闭侧边栏”时
 
        //$('.file-close-sidebar,.sidebar').hide();       //隐藏class=“close-sidebar”和“sidebar”的对象,即导航中“关闭侧边栏”和主题的“侧边栏”
 
 
        if(sideShow){
            $('#side').animate({
                marginLeft: "-250px"
            }, 'fast',function(){
                
                $('#favor-content').hide();
 
                $('#user-content').hide(); 
        
                $('#file-content').hide();
                
                $('#userFile-content').show();
            });
        
            $('#desktop').animate({
                left: "0",
                width: $(window).width()
            },'fast');
        } else {
            $('#favor-content').hide();
 
            $('#user-content').hide(); 
        
            $('#file-content').hide();
            
            $('#userFile-content').show();      //显示class=“show-sidebar”的对象,即导航中“显示侧边栏”
        }
        
        $('#desktop').animate({
            left: "250px",
            width: '-=250px'
        },'fast');
  
        $('#side').animate({
            marginLeft: "0px"
        }, 'fast');
        
        $("#desktop").click(function(){
            sideReturn();
        });
        media.setDragPlace(250, 52, $(window).width()-media.getWidth(), $(window).height()-media.getHeight());

        sideShow = true;

    });
});