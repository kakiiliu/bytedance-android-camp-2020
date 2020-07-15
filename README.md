#### **2020-zju-bytedance**

---

**手机型号：HUAWEI P20** （5.8英寸，2244*1080像素）

采用离线配置的gradle，可能需要手动修改gradle-wrapper.properties这个文件中的路径



**Lec1**

- 简单交互：ImageView, Button, TextView, RadioButton, CheckBox, EditText等
  - 输入框中输入"zjg", "yq"或"zj"，点击"GO"按钮，会有相应的图片显示
  - 单选框（组），复选框简单应用
- 部分交互结果输出log
- git使用初步



**Lec2**

- 实现一个简单的消息界面（通过RecyclerView）
  - 上面4个button可点击并跳转
  - 点击每个item，跳转到新的界面并显示是第几个item
- 屏幕旋转时显示onStop（通过Toast）
- 统计页面所有view的个数
  - 递归
  - getChildCount(), getChildAt()

*P.S.*通过intent进入新的界面，要记得在manifest中添加相应的activity



**Lec3**

- lottie，SeekBar调整进度
- scale，alpha动画并组合
  - 按照老师的要求，但是最大的时候正方形会超出我的手机边界
- TabLayout，ViewPager，Lottie淡出RecyclerView淡入
  - Lottie淡出RecyclerView淡入尚未完成，明日继续



**Lec4**

- 绘制时钟界面，包括时针、分针、秒针（长短、粗细不同）
- 时针、分针、秒针随时间跳动
- 绘制表盘上的数字
- 用点绘制表盘刻度



**Lec5**

- 填充选择图片、选择视频、上传等功能
- 修改id和名字为自己
- 列表仅列出自己上传的视频



**Lec6**

- Room数据库练习--todolist
- 点击加号后跳转到⼀个新页面，输⼊任意内容，点击 CONFIRM 后把内容插⼊数据库中，返回主页并更新主页数据；
- 点击每个 note 前边的 checkbox 能把该条 note 置为“已完成”，并更新数据库和 UI；
- 点击每个 note 后边的 x 能把该条 note 删除， 并更新数据库和 UI

p.s.对数据库进行更改时，要先卸载app再运行重装，否则会闪退（这个问题卡了一天）



**Lec7**

- 使用Glide展示网络图片
  -  加载中、加载失败时有占位图 
  - 实现图片圆角功能
  - 渐变展示
- 播放网络视频（有一定的加载时间）
  - 播放、暂停
  - 进度条（时间），且可以点击、滑动、跳转
  - 横竖平切换（存在亿点点问题，切换之后只能重新开始，而且那个横屏的时候按钮需要按两次才能切回竖屏）



**Lec8**

- 实现自定义录制，录制完屏幕显示录制的视频，并会保存到系统相册中