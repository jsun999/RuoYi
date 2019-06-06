$(function () {
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

var showObjectsContents = function (param) {
    var txt = "<dd class=\"g-clearfix AuPKyz open-enable\" _position=\"0\"\n" +
        "    _cmd_installed=\"1\" _installed=\"1\">"+
        "<span node-type=\"EOGexf\" class=\"EOGexf\">\n" +
        "        <span class=\"icon NbKJexb\">\n" +
        "</span>\n" +
        "    </span>\n" +
        "    <div class=\"creyDgd "+ getType(param['Key']) +"\"></div>\n" +
        "        <div class=\"file-name\" style=\"width:60%\">\n" +
        "        <div class=\"text\">\n" +
        "        <a href=\"javascript:void(0);\" class=\"ltvdXqk\" title="+getFileName(param['Key'])+ ">"+ getFileName(param['Key'])+"</a>\n" +
        "        </div>\n" +
        "<div class=\"operate\">\n" +
        "     <div class=\"button-box-mark\" style=\"display:inline-block;*display:inline;*zoom:1;width:1px;height:1px;line-height:0;\"></div>\n" +
        "          <div class=\"x-button-box\" style=\"position: absolute; top: 0px; line-height: normal; visibility: visible; width: 0px; padding-left: 0px; display: block;\">\n" +
        "              <div style=\"display:none;width:100%;height:100%;z-index:30;position:absolute;top:0;left:0;\"></div>\n" +
        "                     <a class=\"g-button\" data-button-id=\"b9\" data-button-index=\"1\" href=\"javascript:;\" title=\"分享\" style=\"display: inline-block;\">\n" +
        "            <span class=\"g-button-right\">\n" +
        "               <em class=\"icon icon-share\" title=\"分享\"></em>\n" +
        "                           <span class=\"text\" style=\"width: auto;\">分享</span>\n" +
        "                 </span>\n" +
        "                  </a>\n" +
        "                <a class=\"g-button\" data-button-id=\"b11\" data-button-index=\"2\" href=\"javascript:;\" title=\"下载\" style=\"display: inline-block;\">\n" +
        "                              <span class=\"g-button-right\">\n" +
        "                       <em class=\"icon icon-download\" title=\"下载\"></em>\n" +
        "                  <span class=\"text\" style=\"width: auto;\">下载</span>\n" +
        "                            </span>\n" +
        "                        </a>\n" +
        "                            <span class=\"g-dropdown-button tools-more\" style=\"display: inline-block;\">\n" +
        "                      <a class=\"g-button\" data-button-id=\"b29\" data-button-index=\"\" href=\"javascript:;\" title=\"更多\">\n" +
        "                        <span class=\"g-button-right\">\n" +
        "                        <em class=\"icon icon-more\" title=\"更多\"></em>\n" +
        "                      <span class=\"text\" style=\"width: auto;\">更多</span>\n" +
        "                         </span>\n" +
        "                           <i class=\"button-red-light-icon tools-more-red-light-icon\"></i>\n" +
        "                         </a>\n" +
        "                           <span class=\"menu\" style=\"width: 68px;\">\n" +
        "                             <a style=\"display:none;\" data-menu-id=\"b-menu0\" class=\"g-button-menu g-menu-hasIcon\" href=\"javascript:;\">\n" +
        "                     <em class=\"icon icon-share\"></em>分享\n" +
        "                                  </a>\n" +
        "                      <a style=\"display:none;\" data-menu-id=\"b-menu1\" class=\"g-button-menu g-menu-hasIcon\" href=\"javascript:;\">\n" +
        "                 <em class=\"icon icon-download\"></em>下载\n" +
        "                       </a>\n" +
        "                    <a style=\"display: block;\" data-menu-id=\"b-menu2\" class=\"g-button-menu\" href=\"javascript:;\">移动到</a>\n" +
        "                <a style=\"display: block;\" data-menu-id=\"b-menu3\" class=\"g-button-menu\" href=\"javascript:;\">复制到</a>\n" +
        "                <a style=\"display: block;\" data-menu-id=\"b-menu4\" class=\"g-button-menu\" href=\"javascript:;\">重命名</a>\n" +
        "              <a style=\"display: block;\" data-menu-id=\"b-menu5\" class=\"g-button-menu\" href=\"javascript:delObject(\'"+param['Key']+"\');\">删除</a>\n" +
        "                     </span>\n" +
        "              </span>\n" +
        "              </div>\n" +
        "       </div>"+
        "        </div>\n" +
        "        <div class=\"wsbdJ7D\" style=\"width:16%\">"+getSize(param['Size'])+"</div>\n" +
        "        <div class=\"pdgbd47Z\" style=\"width:23%\">"+convertUTCTimeToLocalTime(param['LastModified'])+"</div>\n" +
        "    <div class=\"ufuryDBg\" style=\"width:0%\">\n" +
        "        <span class=\"oyLE7b\" node-type=\"xeLW3p\"></span>\n" +
        "        </div>\n" +
        "        </dd>"
    $("div .vdAfKMb").append(txt);
}



var getFileName = function (fullName) {
    return fullName.substring(fullName.lastIndexOf("/")+1,fullName.length);
}

var showObjectsCommonPrefixes = function (param) {
    var txt = "<dd class=\"g-clearfix AuPKyz open-enable\" _position=\"0\"\n" +
        "    _cmd_installed=\"1\" _installed=\"1\">"+
        "<span node-type=\"EOGexf\" class=\"EOGexf\">\n" +
        "        <span class=\"icon NbKJexb\">\n" +
        "</span>\n" +
        "    </span>\n" +
        "    <div class=\"creyDgd dir-small\"></div>\n" +
        "        <div class=\"file-name\" style=\"width:60%\">\n" +
        "        <div class=\"text\">\n" +
        "        <a href=\"javascript:goDetail(\'"+param['Prefix']+"\');\" class=\"ltvdXqk\" title="+getFileName(param['Prefix'].substring(0,param['Prefix'].length-1))+ ">"+ getFileName(param['Prefix'].substring(0,param['Prefix'].length-1))+"</a>\n" +
        "        </div>\n" +
        "<div class=\"operate\">\n" +
        "     <div class=\"button-box-mark\" style=\"display:inline-block;*display:inline;*zoom:1;width:1px;height:1px;line-height:0;\"></div>\n" +
        "          <div class=\"x-button-box\" style=\"position: absolute; top: 0px; line-height: normal; visibility: visible; width: 0px; padding-left: 0px; display: block;\">\n" +
        "              <div style=\"display:none;width:100%;height:100%;z-index:30;position:absolute;top:0;left:0;\"></div>\n" +
        "                     <a class=\"g-button\" data-button-id=\"b9\" data-button-index=\"1\" href=\"javascript:;\" title=\"分享\" style=\"display: inline-block;\">\n" +
        "            <span class=\"g-button-right\">\n" +
        "               <em class=\"icon icon-share\" title=\"分享\"></em>\n" +
        "                           <span class=\"text\" style=\"width: auto;\">分享</span>\n" +
        "                 </span>\n" +
        "                  </a>\n" +
        "                <a class=\"g-button\" data-button-id=\"b11\" data-button-index=\"2\" href=\"javascript:;\" title=\"下载\" style=\"display: inline-block;\">\n" +
        "                              <span class=\"g-button-right\">\n" +
        "                       <em class=\"icon icon-download\" title=\"下载\"></em>\n" +
        "                  <span class=\"text\" style=\"width: auto;\">下载</span>\n" +
        "                            </span>\n" +
        "                        </a>\n" +
        "                            <span class=\"g-dropdown-button tools-more\" style=\"display: inline-block;\">\n" +
        "                      <a class=\"g-button\" data-button-id=\"b29\" data-button-index=\"\" href=\"javascript:;\" title=\"更多\">\n" +
        "                        <span class=\"g-button-right\">\n" +
        "                        <em class=\"icon icon-more\" title=\"更多\"></em>\n" +
        "                      <span class=\"text\" style=\"width: auto;\">更多</span>\n" +
        "                         </span>\n" +
        "                           <i class=\"button-red-light-icon tools-more-red-light-icon\"></i>\n" +
        "                         </a>\n" +
        "                           <span class=\"menu\" style=\"width: 68px;\">\n" +
        "                             <a style=\"display:none;\" data-menu-id=\"b-menu0\" class=\"g-button-menu g-menu-hasIcon\" href=\"javascript:;\">\n" +
        "                     <em class=\"icon icon-share\"></em>分享\n" +
        "                                  </a>\n" +
        "                      <a style=\"display:none;\" data-menu-id=\"b-menu1\" class=\"g-button-menu g-menu-hasIcon\" href=\"javascript:;\">\n" +
        "                 <em class=\"icon icon-download\"></em>下载\n" +
        "                       </a>\n" +
        "                    <a style=\"display: block;\" data-menu-id=\"b-menu2\" class=\"g-button-menu\" href=\"javascript:;\">移动到</a>\n" +
        "                <a style=\"display: block;\" data-menu-id=\"b-menu3\" class=\"g-button-menu\" href=\"javascript:;\">复制到</a>\n" +
        "                <a style=\"display: block;\" data-menu-id=\"b-menu4\" class=\"g-button-menu\" href=\"javascript:;\">重命名</a>\n" +
        "              <a style=\"display: block;\" data-menu-id=\"b-menu5\" class=\"g-button-menu\" href=\"javascript:delObject(\'"+param['Key']+"\');\">删除</a>\n" +
        "                     </span>\n" +
        "              </span>\n" +
        "              </div>\n" +
        "        </div>"+
        "        </div>\n" +
        "        <div class=\"wsbdJ7D\" style=\"width:16%\">-</div>\n" +
        "        <div class=\"pdgbd47Z\" style=\"width:23%\"></div>\n" +
        "    <div class=\"ufuryDBg\" style=\"width:0%\">\n" +
        "        <span class=\"oyLE7b\" node-type=\"xeLW3p\"></span>\n" +
        "        </div>\n" +
        "        </dd>"
    $("div .vdAfKMb").append(txt);
}

var goDetail = function (dir) {
    currentPath=dir;
    $("div .vdAfKMb").empty();
    changePath(dir);
    $("#FuIxtL").show();
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
        size = limit.toFixed(2) + "B"
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
})
$('div.vdAfKMb').on("mouseleave","dd",function () {
    $(this).removeClass("ehd7py");
})

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
    $("#web-uploader").show();
    addUploadList(file);
    var length = $("em.percent").length;
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
            $("em.speed").eq(length-1).html("("+speed+")");
            // 获取上传进度百分比
            var percent = (transferredAmount * 100.0 / totalAmount).toFixed(2)+"%";
            $("em.percent").eq(length-1).html(percent);
            $("div.process").eq(length-1).css("width",percent);
        }
    }, function (err, result) {
        if(err){
            console.error('Error-->' + err);
        }else{
            if(result.CommonMsg.Status==200){
                $("li.file-list").eq(length-1).removeClass("status-uploading");
                $("li.file-list").eq(length-1).addClass("status-success");
            }
        }
    });
};

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
    var arr = path.split("/");
    return arr[arr.length-2];
}


var uploadCallback = function(transferredAmount, totalAmount, totalSeconds){
// 获取上传平均速率
    var speed = getSize(transferredAmount/ totalSeconds)+"/s";
    $("em.speed").html("("+speed+")");
// 获取上传进度百分比
    var percent = (transferredAmount * 100.0 / totalAmount).toFixed(2)+"%";
    $("em.percent").html(percent);
    $("div.process").css("width",percent);
};
//绑定显示下拉菜单
$("div.vdAfKMb").on("click",".g-dropdown-button",function () {
    $(this).toggleClass("button-open");
})

//删除单个对象
var delObject = function (name) {
    obs.deleteObject({
        Bucket: 'guidang1',
        Key : name
    }, function (err, result) {
        if(err){
            console.log('Error-->' + err);
        }else{
            console.log('Status-->' + result.CommonMsg.Status);
            if(result.CommonMsg.Status=="204"){
                $("div.module-yun-tip").show();
            }
        }
    });
}