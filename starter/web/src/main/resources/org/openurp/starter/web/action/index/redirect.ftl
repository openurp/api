[#if b.ajax]
  [#if target=="iframe"]
  <iframe scrolling="auto" style="min-height: 487px;" width="100%" height="100%" frameborder="0" src="${url}" id="wrapperFrame" onload="setIframeHeight()"></iframe>
  <script>
  function setIframeHeight() {
    var iframe= document.getElementById("wrapperFrame");
    var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
    if (iframeWin.document.body) {
      var bar = document.getElementById("main_siderbar");
      var scollHeight = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
      iframe.height = Math.max(bar.offsetHeight,scollHeight)
    }
  };

  </script>
  [#else]
    <div style="margin: auto;text-align: center;padding-top: 20px;font-size:1.2em">
      <form name="redirectForm" action="${url}" method="get" target="${target}">
        如果页面没有跳转，请点击<a href="javascript:document.redirectForm.submit();" style="color:red"> 这里 </a>。
      </form>
      <script>
        document.redirectForm.submit();
      </script>
    </div>
  [/#if]
[#else]
<!DOCTYPE html>
<html lang="zh_CN">
  <head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="refresh" content="0;url=${url}"/>
  </head>
  <body>
   跳转中...
  </body>
</html>
[/#if]
