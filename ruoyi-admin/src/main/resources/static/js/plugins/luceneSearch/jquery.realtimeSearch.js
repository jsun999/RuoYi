;(function($) {
    "use strict"

    var defaultSetting = {
        searchList: "#searchList",
        keywordInput: "#keywordInput",
        data: []
    };

    $.fn.realtimeSearch = function(options) {
        defaultSetting = $.extend(defaultSetting, options || {});
        var that = this;
        function init() {
            method.bindClick(that);
        }

        init();
    }

    var method = {
        bindClick:  function(obj) {
            $(obj).on("focus",function () {
                var cusList = $("#searchList");
                if (cusList.css("opacity") == 0) {
                    cusList.css("opacity",1);
                    cusList.css("z-index",1);
                    var htmlArr = [];
                    for(var i=0; i<defaultSetting.data.length; i++) {
                        var post = defaultSetting.data[i];
                        htmlArr.push("<li onclick='cusFill(this)' data-customerid='"+ post.customerId +"'>"+post.customerName+"</li>");
                    }
                    $(defaultSetting.searchList).html(htmlArr.join(""));
                    $(defaultSetting.keywordInput).hideseek({
                        highlight: true
                    });

                } else {
                    cusList.css("opacity",0);
                    cusList.css("z-index",-1);
                }
            });
            $(obj).on("blur",function () {
                var cusList = $("#searchList");
                cusList.css("opacity",0);
            });
        }
    }

})(jQuery)

