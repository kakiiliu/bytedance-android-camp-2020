#### **2020-zju-bytedance**

---

**手机型号：HUAWEI P20** （5.8英寸，2244*1080像素）



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
  - getChildCount(), getChildAt

*P.S.*通过intent进入新的界面，要记得在manifest中添加相应的activity