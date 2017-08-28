# EasyBlur

> 用于高斯模糊处理的轮子，目标实现功能：
> - 对任意图片进行可设置强度的高斯模糊处理，并显示到需要的控件上；
> - 弹出以当前画面的高斯模糊为背景的对话框

## DONE
2017-8-28 19:00
- 增加了blur方法对file、path的重载
- 适配了app和v4包两种dialog背景的模糊
- 增加了压缩图片的方法对file的重载

2017-8-28 15:45
- 重写了engine类的逻辑，初步完成背景高斯模糊的对话框，直接复用了前面完成的EasyBlur来进行背景bitmap的高斯模糊处理

2017-8-25 18:40
- 将单一Engine类换为接口和实现类，方便以后扩展
- 添加了本地图片压缩，修复了本地图片占内存太大高斯模糊时会oom的bug
- dialog的动画

2017-8-23 18:40
- 构建与dialogfragment生命周期绑定的engine类
- 将绑定activity的方法从builder类移动到唯一使用那个activity引用的BlurDialog类中，避免了将activity对象序列化放到bundle中来回传的情况

2017-8-22 17：50
- 初步完成由dialogfragment实现的对话框
- 对app包和v4包的dialogfragment进行了封装，通过builder获取初始化对话框需要的数据（后面加上对话框背景高斯模糊也要通过builder来获取数据）

2017-8-21 16：00
- 将只有两种选择方式的代码改为工厂+注解枚举的可扩展的形式

2017-8-21 15：00
- 本地图片进行高斯模糊后显示到指定的imageView上
- 可进行Java高斯模糊和renderScript高斯模糊的选择、以及模糊半径和图像缩放大小的配置

## TODO
2017-8-25 18:40
- ~~engine与dialogfragment生命周期绑定后的操作~~
- 开新线程进行压缩和高斯模糊的操作，避免主线程阻塞

2017-8-22 18:00
- ~~将对话框的背景高斯模糊~~
- 封装的对话框builder还没有将全部的alertdialog功能放进去

2017-8-21 15：00
- ~~网络图片的处理~~
- ~~当前界面的高斯模糊以及对话框功能的实现~~

## THINKING
2017-8-25
- 是否需要使用线程池管理压缩图片的线程，使用cache还是哪种线程池

2017-8-22
- BlurDialog的show方法根据传入activity的instanceof进行了if-else判断，来选择使用app的dialogfragment还是v4包的dialogfragment，这种扩展性不高的方案在目前只有这两种dialogfragment的前提下是否可行
- 为了使用builder模式，不止以后要用到的高斯模糊的初始化数据要放在builder类中，初始化alertdialog所有功能需要的数据也放在了builder类中，这种方案是否可行以及能否改进

2017-8-21
- 高斯模糊处理后是否需要缓存
- 接口设计是否需要改进
- fastBlur算法