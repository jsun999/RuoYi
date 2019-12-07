;(function() {

    AOS.init({
        easing: 'ease-out-back',
        duration: 1000,
        disable: 'mobile'
    });
    AOS.refresh(true);

    // 返回顶部
    var goBack = function() {
        var toTop = $("#toTop");
        $(window).scroll(function(e) {
            var scrollTop = $(this).scrollTop();

            if (scrollTop > 500) {
                toTop.removeClass("to-hide");
            } else {
                if (!toTop.hasClass("to-hide")) {
                    toTop.addClass("to-hide");
                }
            }
        });
        toTop.on("click",function() {
            $('html, body').animate({
                scrollTop: $('html').offset().top
            }, 500);
        });
    }

    // 获取客户列表
    var posts = function () {
        var postList = sessionStorage.getItem("customerList");
        if (postList) {
            return;
        }

        $.ajax({
            "type": "POST",
            "url": "/system/customer/list",
            "async": false,
            "success": function (resp) {
                if (resp.code == 0) {
                    sessionStorage.setItem("customerList",JSON.stringify(resp.rows));
                }
            }
        });
    }

    // 搜索
    var search = function () {
        posts();
        var postListStr = sessionStorage.getItem("customerList");
        var postList = [];
        if (postListStr) {
            postList = JSON.parse(postListStr);
        }
        $("#keywordInput").realtimeSearch({"data":postList});
    }

    $(function() {
        goBack();
        search();
    });

})();

