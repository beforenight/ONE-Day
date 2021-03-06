# ONE-Unofficial


非官方版“**一个(ONE)**”。

[一个] 是一个韩寒主编的电子杂志 (**http://wufazhuce.com/**)。

刚开始接触到“一个”时，深深地被它简洁漂亮的UI设计所打动，当然最核心的是它的内容，每天一更新的金句、文章、问题、创意设计，很投合现在文艺青年的心理。后来作为读者时间长了以后发现广告越来越多，新版本的UI设计越来越不协调，渐渐有些失望。于是有了DIY的想法，在这个项目中改了小部分UI和功能，剃除了广告。

关于Api问题，接口由非授权渠道获取，如有侵权请及时告知，笔者可此项目予以删除。

引用的库
----------
 * [butterknife](https://github.com/JakeWharton/butterknife)
 * [fastjson](https://github.com/alibaba/fastjson)
 * [android-async-http](https://github.com/loopj/android-async-http)
 * [fresco](https://github.com/facebook/fresco)
 * [paper](https://github.com/pilgr/Paper)
 * [android-PullToRefresh](https://github.com/chrisbanes/Android-PullToRefresh)

第三方服务
-----------
* 友盟统计Sdk
* 友盟用户反馈Sdk
* 友盟自动更新Sdk
* Facebook分享Sdk
  
截图
-----------
![首页](http://upload-images.jianshu.io/upload_images/701621-8a579d749bae1f68.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![问题](http://upload-images.jianshu.io/upload_images/701621-643b15d759778233.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![文章](http://upload-images.jianshu.io/upload_images/701621-d82a16bd2a956c53.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Thing](http://upload-images.jianshu.io/upload_images/701621-925b2e8cdcc423b1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![About](http://upload-images.jianshu.io/upload_images/701621-fe12bc0e3b44afc5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![启动页](http://upload-images.jianshu.io/upload_images/701621-3fe3eb1219c5f6f3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![句子](http://upload-images.jianshu.io/upload_images/701621-7ddeb1ca9e99c5f8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

版本
----------
注：建议使用最新代码编译后再预览，代码会不时小幅更新。

*  2016-2-19，修复了一些bug。取消右滑刷新导致页面卡顿。

*  2016-2-3，增加了“首页”、“文章”、“问题”、“东西”四部分左滑刷新的功能，对PullToRefresh有轻微修改。下一版本会完善一些细节的地方。

*  2016-1-26，主要在个人中心增加了“关于”、“分享给好友”、“意见反馈”、“评分”和自动更新功能。下一个版本会增加左滑刷新功能。

*  2016-1-20，这两天修改了下代码，增加了缓存机制，包括图片缓存和文件缓存，限制了最多只能查看往期7天的内容。
首次尝试了使用[paper](https://github.com/pilgr/Paper)，一个优秀的数据存储库，但一个缺点是paper不能设置过期时间，只好自己判断了。

* 2016-1-8，由于工作的关系有一段时间没有维护了，现在拾了起来。从这版中开始使用日期作为版本名，更改了包名为studio.uphie.one。修复了程序长时间后台运行再打开界面重叠的bug，放弃了双击界面“喜欢”的功能。

联系我
------------
* 邮箱: beforenight@163.com

