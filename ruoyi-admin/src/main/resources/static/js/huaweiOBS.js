// var thisIframe;
$(function () {
/*    var frameid = $("div.page-tabs-content").children(".active").data("id");
    var thisFrame = function(){
        $("#side-menu a.menuItem").each(function () {
            if($(this).attr("href")==frameid){
                return $(window.frames["iframe"+$(this).data("index")]);
            }
        });
    };
    thisIframe= thisFrame();*/

    listObjects();
})
var currentPath = "";
var obs = (function getObsClient(){
    /*
    * Initialize a obs client instance with your account for accessing OBS
    */
    var ak = "";
    var sk = "";
    var server = "";
    if(ak==""){
        $.ajax({
            url:ctx + "document/getKey",
            type : "GET",
            async:false,
            success:function (result) {
                if(result.code==0){
                    ak=result.data[0].param_value;
                    sk=result.data[1].param_value;
                    server=result.data[2].param_value;
                }
            }
        });
    }
    return new ObsClient({
        access_key_id: ak,
        secret_access_key: sk,
        server : server,
        timeout : 60 * 5,
    });
})();
/*
 * List objects using default parameters, will return up to 1000 objects
 */
function listObjects(){
    currentPath="";
    $("div .vdAfKMb").empty();
    $("#FuIxtL").hide();
    var bucketName =  "guidang1";
    obs.listObjects({
        Bucket: bucketName,
// 设置文件夹分隔符"/"
        Delimiter: '/'
    }, function (err, result) {
        if(!err && result.CommonMsg.Status < 300){
            for(var k in result.InterfaceResult.CommonPrefixes){
                showObjectsCommonPrefixes(result.InterfaceResult.CommonPrefixes[k])
            }
            for(var j in result.InterfaceResult.Contents){
                showObjectsContents(result.InterfaceResult.Contents[j])
            }
        }
    });
}
var getFolder = function(dir,type){
    if(type==1){
        var arr = dir.split("/")
        if(arr[arr.length-2]==undefined){
            return "全部文件";
        }else{
            return arr[arr.length-2];
        }
    }else if(type==2){
        var arr = dir.split("/")
        if(arr[arr.length-3]==undefined){
            return "全部文件";
        }else{
            return arr[arr.length-3];
        }
    }
}
var showObjectsContents = function (param,searchType) {
    var txt =
        "<dd class=\"g-clearfix AuPKyz open-enable\" _position=\"0\"\n" +
        "_cmd_installed=\"1\" _installed=\"1\" data-type='1'>"+
        "<span node-type=\"EOGexf\" class=\"EOGexf\">\n" +
        "<span class=\"icon NbKJexb\">\n" +
        "</span>\n" +
        "</span>\n" +
        "<div class=\"creyDgd "+ getType(param['Key']) +"\"></div>\n" +
        "<div class=\"file-name\" style=\"width:60%\">\n" +
        "<div class=\"text\">\n" +
        "<a href=\"javascript:void(0);\" class=\"ltvdXqk\" title="+getFileName(param['Key'])+ ">"+ getFileName(param['Key'])+"</a>\n" +
        "</div>\n" +
        "<div class=\"operate\">\n" +
        "<div class=\"button-box-mark\" style=\"display:inline-block;*display:inline;*zoom:1;width:1px;height:1px;line-height:0;\"></div>\n" +
        "<div class=\"x-button-box\" style=\"position: absolute; top: 0px; line-height: normal; visibility: visible; width: 0px; padding-left: 0px; display: block;\">\n" +
        "<div style=\"display:none;width:100%;height:100%;z-index:30;position:absolute;top:0;left:0;\"></div>\n" +
        "<a class=\"g-button\" data-button-id=\"b9\" data-button-index=\"1\" href=\"javascript:share(\'"+param['Key']+"\','1');\" title=\"分享\" style=\"display: inline-block;\">\n" +
        "<span class=\"g-button-right\">\n" +
        "<em class=\"icon icon-share\" title=\"分享\"></em>\n" +
        "<span class=\"text\" style=\"width: auto;\">分享</span>\n" +
        "</span>\n" +
        "</a>\n" +
        "<a class=\"g-button\" data-button-id=\"b11\" data-button-index=\"2\" href=\"javascript:javascript:downloadFile(\'"+param['Key']+"\');\" title=\"下载\" style=\"display: inline-block;\">\n" +
        "<span class=\"g-button-right\">\n" +
        "<em class=\"icon icon-download\" title=\"下载\"></em>\n" +
        "<span class=\"text\" style=\"width: auto;\">下载</span>\n" +
        "</span>\n" +
        "</a>\n" +
        "<span class=\"g-dropdown-button tools-more\" style=\"display: inline-block;\">\n" +
        "<a class=\"g-button\" data-button-id=\"b29\" data-button-index=\"\" href=\"javascript:;\" title=\"更多\">\n" +
        "<span class=\"g-button-right\">\n" +
        "<em class=\"icon icon-more\" title=\"更多\"></em>\n" +
        "<span class=\"text\" style=\"width: auto;\">更多</span>\n" +
        "</span>\n" +
        "<i class=\"button-red-light-icon tools-more-red-light-icon\"></i>\n" +
        "</a>\n" +
        "<span class=\"menu\" style=\"width: 68px;\">\n" +
        "<a style=\"display: block;\" data-menu-id=\"b-menu2\" class=\"g-button-menu\" href=\"javascript:;\">移动到</a>\n" +
        "<a style=\"display: block;\" data-menu-id=\"b-menu3\" class=\"g-button-menu\" href=\"javascript:;\">复制到</a>\n" +
        "<a style=\"display: block;\" data-menu-id=\"b-menu4\" class=\"g-button-menu\" onclick=\"rename(this)\">重命名</a>\n" +
        "<a style=\"display: block;\" data-menu-id=\"b-menu5\" class=\"g-button-menu\" href=\"javascript:delObjectConfirm(\'"+param['Key']+"\');\">删除</a>\n" +
        "</span>\n" +
        "</span>\n" +
        "</div>\n" +
        "</div>"+
        "</div>\n" +
        "<div class=\"wsbdJ7D\" style=\"width:16%\">"+getSize(param['Size'])+"</div>\n"
    if(searchType==1){
        txt+="<div class=\"pdgbd47Z\" style=\"width:13%\">"+convertUTCTimeToLocalTime(param['LastModified'])+"</div>\n" +
        "<div class=\"ufuryDBg\" style=\"width:9%\"><span onclick=\"javascript:goDetail(\'"+param['Key'].substring(0,param['Key'].lastIndexOf("/")+1)+"\');\" class=\"oyLE7b\" node-type=\"xeLW3p\" title="+ getFolder(param['Key'],1) +">"+getFolder(param['Key'],1)+"</span></div>";
    }else {
        txt+="<div class=\"pdgbd47Z\" style=\"width:23%\">"+convertUTCTimeToLocalTime(param['LastModified'])+"</div>\n" +
            "<div class=\"ufuryDBg\" style=\"width:0%\">\n" ;
    }
        txt+=
        "<span class=\"oyLE7b\" node-type=\"xeLW3p\"></span>\n" +
        "</div>\n" +
        "</dd>"
    $("div .vdAfKMb").append(txt);
}
var getFileName = function (fullName) {
    if(fullName.endsWith("/")){
        return fullName.substring(fullName.substring(0,fullName.length-1).lastIndexOf("/")+1,fullName.length-1);
    }else{
        return fullName.substring(fullName.lastIndexOf("/")+1,fullName.length);
    }
}
function addfolderTxt(param,searchType){
    var arr=new Array();
    arr.push("<dd class=\"g-clearfix AuPKyz open-enable\" _position=\"0\"\n");
    arr.push("_cmd_installed=\"1\" _installed=\"1\" data-type='2'>");
    arr.push("<span node-type=\"EOGexf\" class=\"EOGexf\">\n");
    arr.push("<span class=\"icon NbKJexb\">\n");
    arr.push("</span>\n");
    arr.push("</span>\n");
    arr.push("<div class=\"creyDgd dir-small\"></div>\n");
    arr.push("<div class=\"file-name\" style=\"width:60%\">\n");
    arr.push("<div class=\"text\">\n");
    if(param==undefined||param==null){
        arr.push("<a href=\"javascript:;\" class=\"ltvdXqk\" title=\"-\">-</a>\n");
    }else{
        arr.push("<a href=\"javascript:goDetail(\'"+param['Prefix']+"\');\" class=\"ltvdXqk\" title="+getFileName(param['Prefix'].substring(0,param['Prefix'].length-1))+ ">"+ getFileName(param['Prefix'].substring(0,param['Prefix'].length-1))+"</a>\n");
    }
    arr.push("</div>\n");
    arr.push("<div class=\"operate\">\n");
    arr.push("<div class=\"button-box-mark\" style=\"display:inline-block;*display:inline;*zoom:1;width:1px;height:1px;line-height:0;\"></div>\n");
    arr.push("<div class=\"x-button-box\" style=\"position: absolute; top: 0px; line-height: normal; visibility: visible; width: 0px; padding-left: 0px; display: block;\">\n");
    arr.push("<div style=\"display:none;width:100%;height:100%;z-index:30;position:absolute;top:0;left:0;\"></div>\n");
    arr.push("<a class=\"g-button\" data-button-id=\"b9\" data-button-index=\"1\" href=\"javascript:share(\'"+param['Prefix']+"\','2');\" title=\"分享\" style=\"display: inline-block;\">\n");
    arr.push("<span class=\"g-button-right\">\n");
    arr.push("<em class=\"icon icon-share\" title=\"分享\"></em>\n");
    arr.push("<span class=\"text\" style=\"width: auto;\">分享</span>\n");
    arr.push("</span>\n");
    arr.push("</a>\n");
    if(param==undefined||param==null){
        arr.push("<a class=\"g-button\" data-button-id=\"b11\" data-button-index=\"2\" href=\"javascript:;\" title=\"下载\" style=\"display: inline-block;\">\n");
    }else{
        arr.push("<a class=\"g-button\" data-button-id=\"b11\" data-button-index=\"2\" href=\"javascript:downloadFile(\'"+param['Prefix']+"\');\" title=\"下载\" style=\"display: inline-block;\">\n");
    }
    arr.push("<span class=\"g-button-right\">\n");
    arr.push("<em class=\"icon icon-download\" title=\"下载\"></em>\n");
    arr.push("<span class=\"text\" style=\"width: auto;\">下载</span>\n");
    arr.push("</span>\n");
    arr.push("</a>\n");
    arr.push("<span class=\"g-dropdown-button tools-more\" style=\"display: inline-block;\">\n");
    arr.push("<a class=\"g-button\" data-button-id=\"b29\" data-button-index=\"\" href=\"javascript:;\" title=\"更多\">\n");
    arr.push("<span class=\"g-button-right\">\n");
    arr.push("<em class=\"icon icon-more\" title=\"更多\"></em>\n");
    arr.push("<span class=\"text\" style=\"width: auto;\">更多</span>\n");
    arr.push("</span>\n");
    arr.push("<i class=\"button-red-light-icon tools-more-red-light-icon\"></i>\n");
    arr.push("</a>\n");
    arr.push("<span class=\"menu\" style=\"width: 68px;\">\n");
    arr.push("<a style=\"display: block;\" data-menu-id=\"b-menu2\" class=\"g-button-menu\" href=\"javascript:;\">移动到</a>\n");
    arr.push("<a style=\"display: block;\" data-menu-id=\"b-menu3\" class=\"g-button-menu\" href=\"javascript:;\">复制到</a>\n");
    arr.push("<a style=\"display: block;\" data-menu-id=\"b-menu4\" class=\"g-button-menu\" onclick=\"rename(this)\">重命名</a>\n");
    if(param==undefined||param==null){
        arr.push("<a style=\"display: block;\" class=\"g-button-menu\" href=\"javascript:;\">删除</a>\n");
    }else{
        arr.push("<a style=\"display: block;\" class=\"g-button-menu\" href=\"javascript:delObjectConfirm(\'"+param['Prefix']+"\');\">删除</a>\n");
    }
    arr.push("</span>\n");
    arr.push("</span>\n");
    arr.push("</div>\n");
    arr.push("</div>");
    arr.push("</div>\n");
    arr.push("<div class=\"wsbdJ7D\" style=\"width:16%\">-</div>\n");
    if(searchType==1){
        arr.push("<div class=\"pdgbd47Z\" style=\"width:13%\"></div>\n");
        arr.push("<div class=\"ufuryDBg\" style=\"width:9%\"><span onclick=\"javascript:goDetail(\'"+param['Prefix'].substring(0,param['Prefix'].lastIndexOf("/")+1)+"\');\" class=\"oyLE7b\" node-type=\"xeLW3p\" title="+getFolder(param['Prefix'],2)+">"+getFolder(param['Prefix'],2)+"</span></div>");
    }else{
        arr.push("<div class=\"pdgbd47Z\" style=\"width:23%\"></div>\n");
        arr.push("<div class=\"ufuryDBg\" style=\"width:0%\">\n");
    }
    arr.push("<span class=\"oyLE7b\" node-type=\"xeLW3p\"></span>\n");
    arr.push("</div>\n");
    arr.push("</dd>");
    var str=arr.join("");
    return str;
}
var showObjectsCommonPrefixes = function (param,searchType) {
    $("div .vdAfKMb").append(addfolderTxt(param,searchType));
}
var goDetail = function (dir) {
    currentPath=dir;
    $("div .vdAfKMb").empty();
    changePath(dir);
    obs.listObjects({
        Bucket : 'guidang1',
        MaxKeys : 1000,
        Delimiter: '/',
        // 设置文件夹对象名"dir/"为前缀
        Prefix : dir
    }, function (err, result) {
        if(err){
            console.error('Error-->' + err);
        }else{
            if(result.CommonMsg.Status < 300 && result.InterfaceResult){
                for(var k in result.InterfaceResult.CommonPrefixes){
                    showObjectsCommonPrefixes(result.InterfaceResult.CommonPrefixes[k])
                }
                for(var j in result.InterfaceResult.Contents){
                    if(result.InterfaceResult.Contents[j]["Key"]!=dir){
                        showObjectsContents(result.InterfaceResult.Contents[j])
                    }
                }
                if(result.InterfaceResult.IsTruncated === 'true'){
                    listAll(result.InterfaceResult.NextMarker);
                }
            }
        }
    });
}
var changePath = function(dir){
    if(dir==""){
        $("#FuIxtL").hide();
        return;
    }
    $("#linkfolder").empty();
    var txt = "<a href=\"javascript:listObjects();\" title=\"全部文件\">全部文件</a><span class=\"KLxwHFb\">&gt;</span>";
    $("#linkfolder").append(txt);
    var arr = dir.split("/")
    var arr1 = arr.slice(0,-2);
    arr1.forEach(function(i,index){
        var txt1 = "<a href=\"javascript:goDetail(\'"+getPath(dir,index)+"\');\" title=\""+ getPath(dir,index).substring(0,getPath(dir,index).length-1)+"\">"+ i + "</a><span class=\"KLxwHFb\">&gt;</span>";
        $("#linkfolder").append(txt1);
    });
    var txt2 = "<span title="+dir+">"+ arr[arr.length-2] +"</span>";
    $("#linkfolder").append(txt2);
    changeGoback(dir,arr);
    $("#FuIxtL").show();
}
var changeGoback = function (dir,arr){
    if(arr.length>2){
        var path= "";
        var arr1 = arr.slice(0,-2);
        arr1.forEach(function(i,index){
            path+=i+"/"
        });
        $("#backLast").attr("href","javascript:goDetail(\'"+ path +"\')");
    }else{
        $("#backLast").attr("href","javascript:listObjects()");
    }
}
var getPath = function(dir,index){
    var x=dir.indexOf("/");
    for(var i=0;i<index;i++){
        x=dir.indexOf("/",x+1);
    }
    return dir.substring(0,x+1);
}
var getType = function(fileName){
    var type = fileName.substr(fileName.lastIndexOf(".")+1);
    if(fileType[type]==""||fileType[type]==null||fileType[type]==undefined){
        return "default-small";
    }
    return fileType[type][0];
}
var getSize = function (limit){
    var size = "";
    if(limit < 0.1 * 1024){                            //小于0.1KB，则转化成B
        size = limit + "B"
    }else if(limit < 0.1 * 1024 * 1024){            //小于0.1MB，则转化成KB
        size = (limit/1024).toFixed(2) + "KB"
    }else if(limit < 0.1 * 1024 * 1024 * 1024){        //小于0.1GB，则转化成MB
        size = (limit/(1024 * 1024)).toFixed(2) + "MB"
    }else{                                            //其他转化成GB
        size = (limit/(1024 * 1024 * 1024)).toFixed(2) + "GB"
    }

    var sizeStr = size + "";                        //转成字符串
    var index = sizeStr.indexOf(".");                    //获取小数点处的索引
    var dou = sizeStr.substr(index + 1 ,2)            //获取小数点后两位的值
    if(dou == "00"){                                //判断后两位是否为00，如果是则删除00
        return sizeStr.substring(0, index) + sizeStr.substr(index + 3, 2)
    }
    return size;
}
var convertUTCTimeToLocalTime = function (UTCDateString) {
    if(!UTCDateString){
        return '-';
    }
    function formatFunc(str) {    //格式化显示
        return str > 9 ? str : '0' + str
    }
    var date2 = new Date(UTCDateString);     //这步是关键
    var year = date2.getFullYear();
    var mon = formatFunc(date2.getMonth() + 1);
    var day = formatFunc(date2.getDate());
    var hour = date2.getHours();
    // var noon = hour >= 12 ? 'PM' : 'AM';
    // hour = hour>=12?hour-12:hour;
    hour = formatFunc(hour);
    var min = formatFunc(date2.getMinutes());
    var sec = formatFunc(date2.getSeconds());
    var dateStr = year+'-'+mon+'-'+day+' '+hour+':'+min+":"+sec;
    return dateStr;
}
var fileType = {
    "bt": ["fileicon-small-bt", "fileicon-large-bt", "fileicon-middle-bt"],
    "torrent": ["fileicon-small-bt", "fileicon-large-bt", "fileicon-middle-bt"],
    "dws": ["fileicon-small-dws", "fileicon-large-dws", "fileicon-middle-dws"],
    "dwt": ["fileicon-small-dws", "fileicon-large-dws", "fileicon-middle-dws"],
    "dxf": ["fileicon-small-dws", "fileicon-large-dws", "fileicon-middle-dws"],
    "dwg": ["fileicon-small-dws", "fileicon-large-dws", "fileicon-middle-dws"],
    "cad": ["fileicon-small-dws", "fileicon-large-dws", "fileicon-middle-dws"],
    "as": ["fileicon-small-code", "fileicon-large-code"],
    "sh": ["fileicon-small-code", "fileicon-large-code"],
    "c": ["fileicon-small-code", "fileicon-large-code"],
    "cpp": ["fileicon-small-code", "fileicon-large-code"],
    "h": ["fileicon-small-code", "fileicon-large-code"],
    "cs": ["fileicon-small-code", "fileicon-large-code"],
    "asp": ["fileicon-small-code", "fileicon-large-code"],
    "css": ["fileicon-small-code", "fileicon-large-code"],
    "pas": ["fileicon-small-code", "fileicon-large-code"],
    "diff": ["fileicon-small-code", "fileicon-large-code"],
    "patch": ["fileicon-small-code", "fileicon-large-code"],
    "erl": ["fileicon-small-code", "fileicon-large-code"],
    "groovy": ["fileicon-small-code", "fileicon-large-code"],
    "java": ["fileicon-small-code", "fileicon-large-code"],
    "jsp": ["fileicon-small-code", "fileicon-large-code"],
    "js": ["fileicon-small-code", "fileicon-large-code"],
    "json": ["fileicon-small-code", "fileicon-large-code"],
    "pl": ["fileicon-small-code", "fileicon-large-code"],
    "php": ["fileicon-small-code", "fileicon-large-code"],
    "py": ["fileicon-small-code", "fileicon-large-code"],
    "rb": ["fileicon-small-code", "fileicon-large-code"],
    "sass": ["fileicon-small-code", "fileicon-large-code"],
    "scss": ["fileicon-small-code", "fileicon-large-code"],
    "scala": ["fileicon-small-code", "fileicon-large-code"],
    "sql": ["fileicon-small-code", "fileicon-large-code"],
    "vb": ["fileicon-small-code", "fileicon-large-code"],
    "xml": ["fileicon-small-code", "fileicon-large-code"],
    "xhtml": ["fileicon-small-code", "fileicon-large-code"],
    "html": ["fileicon-small-code", "fileicon-large-code"],
    "htm": ["fileicon-small-code", "fileicon-large-code"],
    "md": ["fileicon-small-code", "fileicon-large-code"],
    "less": ["fileicon-small-code", "fileicon-large-code"],
    "lua": ["fileicon-small-code", "fileicon-large-code"],
    "go": ["fileicon-small-code", "fileicon-large-code"],
    "bat": ["fileicon-small-code", "fileicon-large-code"],
    "wml": ["fileicon-small-code", "fileicon-large-code"],
    "txt": ["fileicon-small-txt", "fileicon-large-txt", "fileicon-middle-txt"],
    "rtf": ["fileicon-small-txt", "fileicon-large-txt", "fileicon-middle-txt"],
    "pdf": ["fileicon-small-pdf", "fileicon-large-pdf", "fileicon-middle-pdf"],
    "doc": ["fileicon-small-doc", "fileicon-large-doc", "fileicon-middle-doc"],
    "docx": ["fileicon-small-doc", "fileicon-large-doc", "fileicon-middle-doc"],
    "ppt": ["fileicon-small-ppt", "fileicon-large-ppt", "fileicon-middle-ppt"],
    "pptx": ["fileicon-small-ppt", "fileicon-large-ppt", "fileicon-middle-ppt"],
    "xls": ["fileicon-small-xls", "fileicon-large-xls", "fileicon-middle-xls"],
    "xlsx": ["fileicon-small-xls", "fileicon-large-xls", "fileicon-middle-xls"],
    "vsd": ["fileicon-small-vsd", "fileicon-large-vsd", "fileicon-middle-vsd"],
    "jpg": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "jpeg": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "livp": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "gif": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "bmp": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "png": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "jpe": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "cur": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "svgz": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "tif": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "tiff": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "ico": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "heic": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "heif": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "avci": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "mmap": ["fileicon-small-mmap", "fileicon-large-mmap", "fileicon-middle-mmap"],
    "xmind": ["fileicon-small-xmind", "fileicon-large-xmind", "fileicon-middle-xmind"],
    "mm": ["fileicon-small-mm", "fileicon-large-mm", "fileicon-middle-mm"],
    "wma": ["fileicon-small-mp3", "fileicon-large-mp3", "fileicon-middle-mp3"],
    "wav": ["fileicon-small-mp3", "fileicon-large-mp3", "fileicon-middle-mp3"],
    "mp3": ["fileicon-small-mp3", "fileicon-large-mp3", "fileicon-middle-mp3"],
    "aac": ["fileicon-small-mp3", "fileicon-large-mp3", "fileicon-middle-mp3"],
    "ra": ["fileicon-small-mp3", "fileicon-large-mp3", "fileicon-middle-mp3"],
    "ram": ["fileicon-small-mp3", "fileicon-large-mp3", "fileicon-middle-mp3"],
    "mp2": ["fileicon-small-mp3", "fileicon-large-mp3", "fileicon-middle-mp3"],
    "ogg": ["fileicon-small-mp3", "fileicon-large-mp3", "fileicon-middle-mp3"],
    "aif": ["fileicon-small-mp3", "fileicon-large-mp3", "fileicon-middle-mp3"],
    "mpega": ["fileicon-small-mp3", "fileicon-large-mp3", "fileicon-middle-mp3"],
    "amr": ["fileicon-small-mp3", "fileicon-large-mp3", "fileicon-middle-mp3"],
    "mid": ["fileicon-small-mp3", "fileicon-large-mp3", "fileicon-middle-mp3"],
    "midi": ["fileicon-small-mp3", "fileicon-large-mp3", "fileicon-middle-mp3"],
    "m4a": ["fileicon-small-mp3", "fileicon-large-mp3", "fileicon-middle-mp3"],
    "jpg": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "jpeg": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "gif": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "bmp": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "png": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "jpe": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "cur": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "svgz": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "tif": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "tiff": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "ico": ["fileicon-small-pic", "default-large", "fileicon-middle-pic"],
    "exe": ["fileicon-sys-s-exe", "fileicon-sys-l-exe"],
    "msi": ["fileicon-sys-s-exe", "fileicon-sys-l-exe"],
    "dmg": ["fileicon-sys-s-exe", "fileicon-sys-l-dmg"],
    "pkg": ["fileicon-sys-s-exe", "fileicon-sys-l-dmg"],
    "psd": ["fileicon-sys-s-psd", "fileicon-sys-l-psd"],
    "apk": ["fileicon-sys-s-apk", "fileicon-sys-l-apk"],
    "key": ["fileicon-sys-s-key", "fileicon-sys-l-key"],
    "ai": ["fileicon-sys-s-ai", "fileicon-sys-l-ai"],
    "ipa": ["fileicon-sys-s-ipa", "fileicon-sys-l-ipa"],
    "pages": ["fileicon-sys-s-pages", "fileicon-sys-l-pages"],
    "numbers": ["fileicon-sys-s-numbers", "fileicon-sys-l-numbers"],
    "eot": ["fileicon-sys-s-fonts", "fileicon-sys-l-fonts"],
    "ttf": ["fileicon-sys-s-fonts", "fileicon-sys-l-fonts"],
    "woff": ["fileicon-sys-s-fonts", "fileicon-sys-l-fonts"],
    "eps": ["fileicon-sys-s-eps", "fileicon-sys-l-eps"],
    "lnk": ["fileicon-sys-s-links", "fileicon-sys-l-links"],
    "link": ["fileicon-sys-s-links", "fileicon-sys-l-links"],
    "swf": ["fileicon-sys-s-swf", "fileicon-sys-l-swf"],
    "php": ["fileicon-sys-s-code", "fileicon-sys-l-code"],
    "c": ["fileicon-sys-s-code", "fileicon-sys-l-code"],
    "js": ["fileicon-sys-s-code", "fileicon-sys-l-code"],
    "css": ["fileicon-sys-s-code", "fileicon-sys-l-code"],
    "html": ["fileicon-sys-s-web", "fileicon-sys-l-web"],
    "htm": ["fileicon-sys-s-web", "fileicon-sys-l-web"],
    "xhtml": ["fileicon-sys-s-web", "fileicon-sys-l-web"],
    "java": ["fileicon-sys-s-code", "fileicon-sys-l-code"],
    "cc": ["fileicon-sys-s-code", "fileicon-sys-l-code"],
    "python": ["fileicon-sys-s-code", "fileicon-sys-l-code"],
    "json": ["fileicon-sys-s-code", "fileicon-sys-l-code"],
    "sh": ["fileicon-sys-s-code", "fileicon-sys-l-code"],
    "bat": ["fileicon-sys-s-code", "fileicon-sys-l-code"],
    "ejs": ["fileicon-sys-s-code", "fileicon-sys-l-code"],
    "xml": ["fileicon-sys-s-code", "fileicon-sys-l-code"],
    "ts": ["fileicon-sys-s-video", "fileicon-sys-l-video"],
    "wmv": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "rmvb": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "mpeg4": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "mpeg2": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "flv": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "avi": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "3gp": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "mpga": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "qt": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "rm": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "wmz": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "wmd": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "wvx": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "wmx": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "wm": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "mpg": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "mp4": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "mkv": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "mpeg": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "mov": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "asf": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "m4v": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "m3u8": ["fileicon-small-video", "fileicon-large-video", "fileicon-middle-video"],
    "rar": ["fileicon-small-zip", "fileicon-large-zip", "fileicon-middle-zip"],
    "zip": ["fileicon-small-zip", "fileicon-large-zip", "fileicon-middle-zip"]
}
$("#uploadButton").mouseenter(function () {
    $("#uploadButton").addClass("button-open")
});
$("#uploadButton").mouseleave(function () {
    $("#uploadButton").removeClass("button-open")
});
$('div.vdAfKMb').on("mouseenter","dd",function () {
    $(this).addClass("ehd7py");
});
$('div.vdAfKMb').on("mouseleave","dd",function () {
    $(this).removeClass("ehd7py");
});
$('#web-uploader div.dialog-control').eq(0).on("click","span.icon-minimize",function () {
    $("#web-uploader div.dialog-header").hide();
    $("#web-uploader div.dialog-min-header").show();
    $("#web-uploader div.dialog-body").hide();
});
$('#web-uploader div.dialog-control').eq(0).on("click","span.icon-close",function () {
    $("#web-uploader").hide();
    clearUploadList();
});
$('#web-uploader div.dialog-control').eq(1).on("click","span.icon-maximizing",function () {
    $("#web-uploader div.dialog-header").show();
    $("#web-uploader div.dialog-min-header").hide();
    $("#web-uploader div.dialog-body").show();
});
$('#web-uploader div.dialog-control').eq(1).on("click","span.icon-close",function () {
    $("#web-uploader").hide();
    clearUploadList();
});
$(document).ready(function(){
    $("#h5Input1").change(function () {
        if($(this).val() != ""){
            uploadFile($(this)[0].files[0]);
        }
    });
    $("#h5Input2").change(function () {
        if($(this).val() != ""){
            uploadFileFolder($(this)[0]);
        }
    });
});
//上传文件夹
var uploadFileFolder = function(file){
    for(var i of file.files){
        uploadFile(i);
    }
}
//上传单个文件
var uploadFile = function(file){
    $("#web-uploader em.select-text").eq(0).text("上传中");
    $("#web-uploader em.select-text").eq(1).text("上传中");
    $("#web-uploader").show();
    $("#web-downloader").hide();
    addUploadList(file);
    var length = $("#uploaderList em.percent").length;
    var name;
    if(file.webkitRelativePath==""){
        name = getUploadFileName(file.name);
    }else{
        name = getUploadFileName(file.webkitRelativePath);
    }
    obs.putObject({
        Bucket : 'guidang1',
        Key : name,
        SourceFile : file,
        ProgressCallback: function(transferredAmount, totalAmount, totalSeconds){
            // 获取上传平均速率
            var speed = getSize(transferredAmount/ totalSeconds)+"/s";
            $("#uploaderList em.speed").eq(length-1).html("("+speed+")");
            // 获取上传进度百分比
            var percent = (transferredAmount * 100.0 / totalAmount).toFixed(2)+"%";
            $("#uploaderList em.percent").eq(length-1).html(percent);
            $("#uploaderList div.process").eq(length-1).css("width",percent);
        }
    }, function (err, result) {
        if(err){
            console.error('Error-->' + err);
        }else{
            if(result.CommonMsg.Status==200){
                $("#uploaderList li.file-list").eq(length-1).removeClass("status-uploading");
                $("#uploaderList li.file-list").eq(length-1).addClass("status-success");
                goDetail(currentPath);
            }
        }
    });
};
var clearUploadList = function () {
    $("#uploaderList").empty();
}
var addUploadList = function(file){
    var txt = "<li class=\"file-list status-uploading\">\n" +
        "                        <div class=\"process\"></div>\n" +
        "                        <div class=\"info\">\n" +
        "                            <div class=\"file-name\" title=\""+ file.name +"\">\n" +
        "                                <div class=\"file-icon fileicon-small-video\"></div>\n" +
        "                                <span class=\"name-text\">"+ file.name +"</span></div>\n" +
        "                            <div class=\"file-size\">"+ getSize(file.size) +"</div>\n" +
        "                            <div class=\"file-path\">\n" +
        "                                <a title=\""+ currentPath +"\" class=\"server_path\" href=\"javascript:goDetail(\'"+currentPath+"\');\">" + getFoldername(currentPath) +"</a>\n" +
        "                            </div>\n" +
        "                            <div class=\"file-status\">\n" +
        "                                <span class=\"waiting\">排队中…</span>\n" +
        "                                <span class=\"prepare\">准备上传…</span>\n" +
        "                                <span class=\"uploading\">\n" +
        "                                    <em class=\"percent\"></em>\n" +
        "                                    <em class=\"speed\"></em>\n" +
        "                                </span>\n" +
        "                                <span class=\"error\">\n" +
        "                                    <em></em>\n" +
        "                                    <i>服务器错误</i>\n" +
        "                                    <b></b>\n" +
        "                                </span>\n" +
        "                                <span class=\"caution\">\n" +
        "                                    <em></em>\n" +
        "                                    <i>服务器错误</i>\n" +
        "                                    <b></b>\n" +
        "                                </span>\n" +
        "                                <span class=\"pause\">\n" +
        "                                    <em></em>\n" +
        "                                    <i>已暂停</i>\n" +
        "                                </span>\n" +
        "                                <span class=\"cancel\">\n" +
        "                                    <em></em>\n" +
        "                                    <i>已取消</i>\n" +
        "                                </span>\n" +
        "                                <span class=\"success\">\n" +
        "                                    <em></em>\n" +
        "                                    <i>上传成功</i>\n" +
        "                                </span></div>\n" +
        "                            <div class=\"file-operate\">\n" +
        "                                <em class=\"operate-pause\"></em>\n" +
        "                                <em class=\"operate-continue\"></em>\n" +
        "                                <em class=\"operate-retry\"></em>\n" +
        "                                <em class=\"operate-remove\"></em>\n" +
        "                                <a class=\"error-link\" href=\"javascript:void(0);\">点我解决</a>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </li>";
    $("#uploaderList").append(txt);
};
//云上的文件名
var getUploadFileName = function(name){
    return currentPath+name;
};
var getFoldername = function (path) {
    if(path==""){
        return "全部文件";
    }
    var arr = path.split("/");
    return arr[arr.length-2];
}
var uploadCallback = function(transferredAmount, totalAmount, totalSeconds){
// 获取上传平均速率
    var speed = getSize(transferredAmount/ totalSeconds)+"/s";
    $("uploaderList em.speed").html("("+speed+")");
// 获取上传进度百分比
    var percent = (transferredAmount * 100.0 / totalAmount).toFixed(2)+"%";
    $("uploaderList em.percent").html(percent);
    $("uploaderList div.process").css("width",percent);
};
//绑定显示下拉菜单
$("div.vdAfKMb").on("click",".g-dropdown-button",function () {
    $(this).toggleClass("button-open");
})
$('div.vdAfKMb').on("mouseleave",".g-dropdown-button",function () {
    $(this).removeClass("button-open");
})
//弹出删除框
var delObjectConfirm = function(name){
    $("#confirm").show();
    $("div.module-canvas").show();
    $("#confirm a.g-button-blue-large").attr("data-filename",name);
}
//删除对象
var delObject = function () {
    $("#confirm").hide();$("div.module-canvas").hide();
    var fileName = $("#confirm a.g-button-blue-large").attr("data-filename");
    if(fileName.endsWith("/")){
        delFileFolder(fileName);
    }else{
        delSingleObject(fileName);
    }
}
//删除单个对象
var delSingleObject = function (fileName) {
    obs.deleteObject({
        Bucket: 'guidang1',
        Key : fileName
    }, function (err, result) {
        if(err){
            console.log('Error-->' + err);
        }else{
            if(result.CommonMsg.Status=="204"){
                goDetail(currentPath);
                $("div.module-yun-tip").show().delay (2000).fadeOut();
            }
        }
    });
}
//删除文件夹下的对象
var delFileFolder = function(dir){
    var fileList = [];
    obs.listObjects({
        Bucket : 'guidang1',
        MaxKeys : 1000,
        // Delimiter: '/',
        // 设置文件夹对象名"dir/"为前缀
        Prefix : dir
    }, function (err, result) {
        if(err){
            console.error('Error-->' + err);
        }else{
            if(result.CommonMsg.Status < 300 && result.InterfaceResult){
                for(var j in result.InterfaceResult.Contents){
                    var xx={Key:result.InterfaceResult.Contents[j].Key};
                    fileList.push(xx);
                }
                obs.deleteObjects({
                    Bucket: 'guidang1',
                    // 设置为verbose模式
                    Quiet : false,
                    Objects : fileList
                }, function (err, result) {
                    if(err){
                        console.log('Error-->' + err);
                    }else{
                        console.log('Status-->' + result.CommonMsg.Status);
                        if(result.CommonMsg.Status < 300 && result.InterfaceResult){
                            goDetail(currentPath);
                            $("div.module-yun-tip").show().delay (2000).fadeOut ();;
                        }
                    }
                });
            }
        }
    });

}
//取消删除
var cancelDel = function(){
    $("#confirm").hide();$("div.module-canvas").hide();
}
var downloadFile = function(filename){
    if(filename.endsWith("/")){
        downloadFileFolder(filename);
    }else{
        downloadSingleObject(filename);
    }
}
var downloadSingleObject = function(file){
    $("#web-downloader em.select-text").text("下载中");
    $("#web-uploader").hide();
    $("#web-downloader").show();
    addDownloadList(file);
    var length = $("#downloaderList em.percent").length;
    obs.getObject({
        Bucket : 'guidang1',
        Key : file,
        SaveByType : 'arraybuffer',
        ProgressCallback:function(transferredAmount, totalAmount, totalSeconds){
            $("#downloaderList div.file-size").eq(length-1).html(getSize(totalAmount));
            // 获取上传平均速率
            var speed = getSize(transferredAmount/ totalSeconds)+"/s";
            $("#downloaderList em.speed").eq(length-1).html("("+speed+")");
            // 获取上传进度百分比
            var percent = (transferredAmount * 100.0 / totalAmount).toFixed(2)+"%";
            $("#downloaderList em.percent").eq(length-1).html(percent);
            $("#downloaderList div.process").eq(length-1).css("width",percent);
            if(transferredAmount==totalAmount){
                $("#downloaderList li.file-list").eq(length-1).removeClass("status-uploading");
                $("#downloaderList li.file-list").eq(length-1).addClass("status-success");
                goDetail(currentPath);
            }

        }
    }, function (err, result) {
        if(err){
            console.error('Error-->' + err);
        }else{
            console.log('Status-->' + result.CommonMsg.Status);
            if(result.CommonMsg.Status < 300 && result.InterfaceResult){
                // 获取下载对象的路径
                // window.open(result.InterfaceResult.Content.SignedUrl)
                // var $eleForm = $("<form method='get'></form>");
                // $eleForm.attr("action",result.InterfaceResult.Content.SignedUrl);
                // $(document.body).append($eleForm);
                // //提交表单，实现下载
                // $eleForm.submit();
                var data = new Blob([result.InterfaceResult.Content]);
                var downloadUrl = window.URL.createObjectURL(data);
                var anchor = document.createElement("a");
                anchor.href = downloadUrl;
                anchor.download = getFileName(file);
                anchor.click();
                window.URL.revokeObjectURL(data);
            }
        }
    });
};
var downloadFileFolder = function (dir) {
    var fileList=[];
    obs.listObjects({
        Bucket : 'guidang1',
        MaxKeys : 1000,
        // Delimiter: '/',
        // 设置文件夹对象名"dir/"为前缀
        Prefix : dir
    }, function (err, result) {
        if(err){
            console.error('Error-->' + err);
        }else{
            if(result.CommonMsg.Status < 300 && result.InterfaceResult){
                for(var j in result.InterfaceResult.Contents){
                    fileList.push(result.InterfaceResult.Contents[j].Key);
                }
                dcb(fileList,downloadSingleObject);
            }
        }
    });
}
var dcb = function(arr,cb){
    for (var i of arr){
        (function (i) {
            cb(i);
        })(i);
    }
}
var addDownloadList = function(file){
    var txt = "<li class=\"file-list status-uploading\">\n" +
        "                        <div class=\"process\"></div>\n" +
        "                        <div class=\"info\">\n" +
        "                            <div class=\"file-name\" title=\""+ getFileName(file) +"\">\n" +
        "                                <div class=\"file-icon fileicon-small-video\"></div>\n" +
        "                                <span class=\"name-text\">"+ getFileName(file) +"</span></div>\n" +
        "                            <div class=\"file-size\">"+ "" +"</div>\n" +
        "                            <div class=\"file-status\">\n" +
        "                                <span class=\"waiting\">排队中…</span>\n" +
        "                                <span class=\"prepare\">准备下载…</span>\n" +
        "                                <span class=\"uploading\">\n" +
        "                                    <em class=\"percent\"></em>\n" +
        "                                    <em class=\"speed\"></em>\n" +
        "                                </span>\n" +
        "                                <span class=\"error\">\n" +
        "                                    <em></em>\n" +
        "                                    <i>服务器错误</i>\n" +
        "                                    <b></b>\n" +
        "                                </span>\n" +
        "                                <span class=\"caution\">\n" +
        "                                    <em></em>\n" +
        "                                    <i>服务器错误</i>\n" +
        "                                    <b></b>\n" +
        "                                </span>\n" +
        "                                <span class=\"pause\">\n" +
        "                                    <em></em>\n" +
        "                                    <i>已暂停</i>\n" +
        "                                </span>\n" +
        "                                <span class=\"cancel\">\n" +
        "                                    <em></em>\n" +
        "                                    <i>已取消</i>\n" +
        "                                </span>\n" +
        "                                <span class=\"success\">\n" +
        "                                    <em></em>\n" +
        "                                    <i>下载成功</i>\n" +
        "                                </span></div>\n" +
        "                            <div class=\"file-operate\">\n" +
        "                                <em class=\"operate-pause\"></em>\n" +
        "                                <em class=\"operate-continue\"></em>\n" +
        "                                <em class=\"operate-retry\"></em>\n" +
        "                                <em class=\"operate-remove\"></em>\n" +
        "                                <a class=\"error-link\" href=\"javascript:void(0);\">点我解决</a>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </li>";
    $("#downloaderList").append(txt);
};
$('#web-downloader div.dialog-control').eq(0).on("click","span.icon-minimize",function () {
    $("#web-downloader div.dialog-header").hide();
    $("#web-downloader div.dialog-min-header").show();
    $("#web-downloader div.dialog-body").hide();
});
$('#web-downloader div.dialog-control').eq(0).on("click","span.icon-close",function () {
    $("#web-downloader").hide();
    $("#downloaderList").empty();
});
$('#web-downloader div.dialog-control').eq(1).on("click","span.icon-maximizing",function () {
    $("#web-downloader div.dialog-header").show();
    $("#web-downloader div.dialog-min-header").hide();
    $("#web-downloader div.dialog-body").show();
});
$('#web-downloader div.dialog-control').eq(1).on("click","span.icon-close",function () {
    $("#web-downloader").hide();
    $("#downloaderList").empty();
});
//新建文件夹：1；重命名：2
var neworrename=0;
var newFileFolder = function () {
    neworrename=1;
    if($("div.ExFGye").is(':hidden')){
        $("div.vdAfKMb").prepend(addfolderTxt());
        $("div.ExFGye").show();
        $("input.GadHyA").val("新建文件夹");
        $("input.GadHyA").focus();
        $("input.GadHyA").select();
    }else{
        return;
    }
}
$("span.fvdQMx").on("click",function () {
    var folderName="";
    if(neworrename==1){
        folderName = currentPath + $(this).prev().val()+"/";
        obs.putObject({
            Bucket : 'guidang1',
            Key : folderName
        }, function (err, result) {
            if(err){
                console.error('Error-->' + err);
            }else{
                if(result.CommonMsg.Status==200){
                    $("div.ExFGye").hide();
                    goDetail(currentPath);
                }
            }
        });
    }
    if(neworrename==2){
        var originname = $("div.vdAfKMb").children().eq($("div.ExFGye").data("index")).find("a.ltvdXqk").text();
        folderName = currentPath + $(this).prev().val();
        obs.copyObject({
            Bucket: 'guidang1',
            Key : folderName,
            CopySource:'guidang1/'+currentPath + originname
        }, function (err, result) {
            if(err){
                console.log('Error-->' + err);
            }else{
                console.log('Status-->' + result.CommonMsg.Status);
            }
        });
    }
});
$("span.gtLWGp").on("click",function () {
    $("div.ExFGye").hide();
    $("input.GadHyA").val("");
    if(neworrename==1){//新建文件夹
        $("div.vdAfKMb").children().eq(0).remove();
    }
});
var rename = function(obj){
    neworrename=2;
    var index = $(obj).parents("dd").parent().children().index($(obj).parents("dd"));
    $("div.ExFGye").css("top",(58+44*index)+"px");
    $("div.ExFGye").data("index",index);
    $("div.ExFGye").show();
    $("input.GadHyA").val($(obj).parents("dd").find("a.ltvdXqk").text());
    $("input.GadHyA").focus();
    $("input.GadHyA").select();

}
$("#searchCur").on("click",function () {
    var patt = new RegExp("\\w*"+$(this).prev().prev().val()+"\\w*");
    $("div .vdAfKMb").empty();
    changePath1(currentPath,$(this).prev().prev().val());
    obs.listObjects({
        Bucket : 'guidang1',
        MaxKeys : 1000,
        Delimiter: '/',
        // 设置文件夹对象名"dir/"为前缀
        Prefix : currentPath
    }, function (err, result) {
        if(err){
            console.error('Error-->' + err);
        }else{
            if(result.CommonMsg.Status < 300 && result.InterfaceResult){
                for(var k in result.InterfaceResult.CommonPrefixes){
                    if(patt.test(getFileName(result.InterfaceResult.CommonPrefixes[k]['Prefix']))){
                        showObjectsCommonPrefixes(result.InterfaceResult.CommonPrefixes[k])
                    }
                }
                for(var j in result.InterfaceResult.Contents){
                    if(patt.test(getFileName(result.InterfaceResult.Contents[j]['Key']))){
                        showObjectsContents(result.InterfaceResult.Contents[j])
                    }
                }
            }
        }
    });
});
var changePath1 = function(dir,searchContent){
    $("#linkfolder").empty();
    var txt = "<a href=\"javascript:listObjects();\" title=\"全部文件\">全部文件</a><span class=\"KLxwHFb\">&gt;</span>";
    $("#linkfolder").append(txt);
    var arr = dir.split("/")
    var arr1 = arr.slice(0,-1);
    arr1.forEach(function(i,index){
        var txt1 = "<a href=\"javascript:goDetail(\'"+getPath(dir,index)+"\');\" title=\""+ getPath(dir,index).substring(0,getPath(dir,index).length-1)+"\">"+ i + "</a><span class=\"KLxwHFb\">&gt;</span>";
        $("#linkfolder").append(txt1);
    });

    var txt2 = "<span title="+dir+">搜索:"+ searchContent +"</span>";
    $("#linkfolder").append(txt2);
    changeGoback(dir,arr);
    $("#FuIxtL").show();
}
$('#searchCurInput').bind('input propertychange', function() {
    if($(this).val()==""){
        goDetail(currentPath);
    }else{
        $("#curSearchDel").show();
    }
});
$("#curSearchDel").on("click",function () {
    $("#searchCurInput").val("");
    $("#curSearchDel").hide();
});
$("#searchAll").on("click",function () {
    var patt = new RegExp("\\w*"+$(this).prev().prev().val()+"\\w*");
    $("div .vdAfKMb").empty();
    $("li.MCGAxG").css("width","13%");
    var txt =
        "<li data-key=\"path-info\" class=\"fufHyA BEPxaPb gObdAzb \" style=\"width:10%;\">\n" +
        "<span class=\"text\">所在目录</span>\n" +
        "<span class=\"xEuDywb\"></span>\n" +
        "<span class=\"icon aHEytd icon-up\"></span>\n" +
        "<span class=\"icon sFxCFbb icon-downtitle\"></span>\n" +
        "</li>"
    $("ul.QAfdwP").append(txt);
    changePath2("",$(this).prev().prev().val());
    obs.listObjects({
        Bucket: 'guidang1',
        Delimiter: '/'
    }).then(function(result)  {
        if(result.CommonMsg.Status < 300){
            for(var k in result.InterfaceResult.CommonPrefixes){
                if(patt.test(result.InterfaceResult.CommonPrefixes[k]['Prefix'])){
                    showObjectsCommonPrefixes(result.InterfaceResult.CommonPrefixes[k],1)
                }
            }
            for(var j in result.InterfaceResult.Contents){
                if(patt.test(result.InterfaceResult.Contents[j]['Key'])){
                    showObjectsContents(result.InterfaceResult.Contents[j],1)
                }
            }

            var listObjectsByPrefix = function(commonPrefixes){
                var i=0;
                for(;i<commonPrefixes.length;i++){
                    obs.listObjects({
                        Bucket: 'guidang1',
                        Delimiter: '/',
                        Prefix: commonPrefixes[i]['Prefix']
                    }).then((function(i){
                        return function(result){
                            if(result.CommonMsg.Status < 300){
                                for(var k in result.InterfaceResult.CommonPrefixes){
                                    if(patt.test(getFileName(result.InterfaceResult.CommonPrefixes[k]['Prefix']))){
                                        showObjectsCommonPrefixes(result.InterfaceResult.CommonPrefixes[k],1)
                                    }
                                }
                                for(var j in result.InterfaceResult.Contents){
                                    if(patt.test(getFileName(result.InterfaceResult.Contents[j]['Key']))&&result.InterfaceResult.Contents[j]['Key']!=commonPrefixes[i]['Prefix']){
                                        showObjectsContents(result.InterfaceResult.Contents[j],1)
                                    }
                                }
                                if(result.InterfaceResult.CommonPrefixes && result.InterfaceResult.CommonPrefixes.length > 0){
                                    listObjectsByPrefix(result.InterfaceResult.CommonPrefixes);
                                }
                            }
                        };
                    })(i));
                }
            };

            listObjectsByPrefix(result.InterfaceResult.CommonPrefixes);
        }
    });
});
var changePath2 = function(dir,searchContent){
    $("#linkfolder").empty();
    var txt = "<a href=\"javascript:listObjects();\" title=\"全部文件\">全部文件</a><span class=\"KLxwHFb\">&gt;</span>";
    $("#linkfolder").append(txt);
    var txt2 = "<span title="+dir+">搜索:"+ searchContent +"</span>";
    $("#linkfolder").append(txt2);
    changeGoback(dir,[]);
    $("#FuIxtL").show();
}
$('#searchAllInput').bind('input propertychange', function() {
    if($(this).val()==""){
        goDetail(currentPath);
    }else{
        $("#allSearchDel").show();
    }
});
$("#allSearchDel").on("click",function () {
    $("#searchAllInput").val("");
    $("#allSearchDel").hide();
});
$('div.zJMtAEb').on("click","span.EOGexf",function () {
    $(this).parent().toggleClass("zwcb105L");
    $("a.g-button").eq(2).show();
    $("a.g-button").eq(3).show();
    $("a.g-button").eq(4).show();
    $("a.g-button").eq(5).show();
    $("a.g-button").eq(6).show();
});
$('div.zJMtAEb').on("dblclick","dd.g-clearfix",function () {
    if($(this).data("type")==2){
        $(this).find("a.ltvdXqk")[0].click();
    }
});
var checkShareTime = function(obj){
    if(0<$(obj).val()&&$(obj).val()<=1080){
        $(obj).css("border","1px solid #e9e9e9")
        return;
    }else{
        $(obj).css("border","1px solid #ff0000")
        alert("请输入0到1080的正整数")
    }
}
var inputTime = function(obj){
    $(obj).css("border","1px solid #39f")
}
// 分享
var share = function(objname,type) {
    if(type==1){
        $("#share em.select-text").text("分享文件");
        $("#shareFileName").text(getFileName(objname));
        $("#urlArea").show();
        $("#share").show();
        $("#shareBtnTd").hide();
        $("#copyTypeTd").show();
        $("div.module-canvas").show();
        $("#sharePath").val(objname);
        createShare($("#shareTime").val()*60,objname)
    }else if(type==2){
        $("#share em.select-text").text("分享文件夹");
        $("#copyTypeTd").hide();
        $("#shareFileName").text(getFileName(objname));
        $("#urlArea").hide();
        $("#share").show();
        $("div.module-canvas").show();
        $("#shareBtnTd").show();
        $("#createShareBtn").data("objname",objname);
    }
}
var closeShare = function(){
    $("#share").hide();
    $("div.module-canvas").hide();
}
var copyURL = function(){
    var oContent=document.getElementById("shareURL");
    oContent.select();
    document.execCommand("Copy");
    $("#share div.create-success span.public").eq(0).show().delay (2000).fadeOut();
}
var copyPath =function(){
    var oContent=document.getElementById("sharePath");
    oContent.select();
    document.execCommand("Copy");
    $("#share div.create-success span.public").eq(1).show().delay (2000).fadeOut();
}
var createShare = function (time,objname) {
    $("#copyTypeTd").show();
    $("#urlArea").show();
    $("#shareBtnTd").hide();
    if(objname==null||objname==undefined){
        objname=$("#createShareBtn").data("objname");
    }
    if(time==null||time==undefined){
        time=$("#shareTime").val()*60;
    }
    var bucketName = 'guidang1';
    var method = 'GET';
    var res = obs.createSignedUrlSync({
        Method: method,
        Bucket: bucketName,
        Key: objname,
        Expires: time,
    });
    var reopt = {
        method: method,
        url: res.SignedUrl,
        withCredentials: false,
        headers: res.ActualSignedRequestHeaders || {},
        validateStatus: function (status) {
            return status >= 200;
        },
        maxRedirects: 0,
        responseType: 'text',
    };
    axios.request(reopt).then(function (response) {
        if (response.status < 300) {
            console.log('Getting object using temporary signature succeed.');
            $("#share textarea").val(response.config.url);
        } else {
            console.log('Getting object using temporary signature failed!');
            console.log('status:' + response.status);
            console.log('\n');
        }
    }).catch(function (err) {
        console.log('Getting object using temporary signature failed!');
        console.log(err);
        console.log('\n');
    });
}
var listShare = function (time,objname) {
    $("#copyTypeTd").show();
    $("#urlArea").show();
    $("#shareBtnTd").hide();
    if(objname==null||objname==undefined){
        objname=$("#createShareBtn").data("objname");
    }
    if(time==null||time==undefined){
        time=$("#shareTime").val()*60;
    }
    var bucketName = 'guidang1';
    var method = 'GET';
    var res = obs.createSignedUrlSync({
        Method : method,
        Bucket : bucketName,
        Expires : time,
    });
    var reopt = {
        method : method,
        url : res.SignedUrl,
        withCredentials: false,
        headers : res.ActualSignedRequestHeaders || {},
        validateStatus: function(status){
            return status >= 200;
        },
        maxRedirects : 0,
        responseType : 'text',
    };
    axios.request(reopt).then(function (response) {
        if(response.status < 300){
            console.log('Listing object using temporary signature succeed.');
            $("#share textarea").val(response.config.url);
        }else{
            console.log('Listing object using temporary signature failed!');
            console.log('status:' + response.status);
            console.log('\n');
        }
    }).catch(function (err) {
        console.log('Listing object using temporary signature failed!');
        console.log(err);
        console.log('\n');
    });
}
