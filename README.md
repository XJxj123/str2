# str2![img](file:///C:\Users\DELL\AppData\Local\Temp\ksohtml24252\wps1.jpg) 

** **

**实验报告******

** **

**实验名称：****古诗字符串处理程序设计****     ****      ****      **** ******

**课程名称：****Java程序设计         **** ****   ******

** **

** **

 

|      |                                          |
| ---- | ---------------------------------------- |
|      | ![img](file:///C:\Users\DELL\AppData\Local\Temp\ksohtml24252\wps2.png) |

 

 

 

 

信息工程学院计算机

 

 

# **实验目的******

利用字符串String及其方法对古诗做对齐处理

设计系统的输入/输出，把处理结果保存在文件中存储

包含异常处理结构

系统的界面采用二选一：

使用GUI窗体界面，设计各组件，完成用户与系统的交互

控制台（Console）

# **任务******

利用已学的字符串处理知识编程完成《长恨歌》古诗的整理对齐工作，达到如下功能：

每7个汉字加入一个标点符号，奇数时加“，”，偶数时加“。”

允许提供输入参数，统计古诗中某个字或词出现的次数

考虑操作中可能出现的异常，在程序中设计异常处理程序

输入：

通过在控制台键盘输入或在GUI窗体中输入待处理的字符串：

汉皇重色思倾国御宇多年求不得杨家有女初长成养在深闺人未识天生丽质难自弃一朝选在君王侧回眸一笑百媚生六宫粉黛无颜色春寒赐浴华清池温泉水滑洗凝脂侍儿扶起娇无力始是新承恩泽时云鬓花颜金步摇芙蓉帐暖度春宵春宵苦短日高起从此君王不早朝承欢侍宴无闲暇春从春游夜专夜后宫佳丽三千人三千宠爱在一身金屋妆成娇侍夜玉楼宴罢醉和春姊妹弟兄皆列士可怜光采生门户遂令天下父母心不重生男重生女骊宫高处入青云仙乐风飘处处闻缓歌慢舞凝丝竹尽日君王看不足渔阳鼙鼓动地来惊破霓裳羽衣曲九重城阙烟尘生千乘万骑西南行<未完，待续>

注意：不能把上述的字符串硬编码在程序中，要通过控制台键盘输入或在GUI窗体中输入。

 

输出：

在程序界面中输出结果，并把结果保存在文件中。

 

汉皇重色思倾国，御宇多年求不得。

杨家有女初长成，养在深闺人未识。

天生丽质难自弃，一朝选在君王侧。

回眸一笑百媚生，六宫粉黛无颜色。

春寒赐浴华清池，温泉水滑洗凝脂。

侍儿扶起娇无力，始是新承恩泽时。

云鬓花颜金步摇，芙蓉帐暖度春宵。

春宵苦短日高起，从此君王不早朝。

…………





核心代码：**

​		public class JFrameWindow extends JFrame {     //需要继承JFrame

​    public JFrameWindow(String title) {

​        JFrame jf = new JFrame(title);

​        Container container = jf.getContentPane();    //得到窗口的容器

 

​        //获取当前屏幕大小

​        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

​        //自定义一些窗口控件的长宽高

​        Dimension newPrefSize = new Dimension(400,200);

​        Dimension jLabelPrefSize = new Dimension(400,20);

​        int width = 450;

​        int height = 800;

​        //将窗口设置在屏幕的正中心

​        int x = ((int) screenSize.getWidth() - width) / 2;

​        int y = ((int) screenSize.getHeight() - height) / 2;

 

​        //第一个文本域的实例化

​        JTextArea area1 = new JTextArea(10,20);

​        area1.setPreferredSize(newPrefSize);

​        area1.setLineWrap(true);

 

​        //第二个结果文本域的实例化

​        JTextArea area2 = new JTextArea(10,20);

​        area2.setPreferredSize(newPrefSize);

​        area2.setLineWrap(true);

​        area2.setEnabled(false);//设置不可以改变的属性

 

​        //标签提示的实例化

​        JLabel jLabel = new JLabel();

​        jLabel.setPreferredSize(jLabelPrefSize);

​        jLabel.setText("查询重复字段：");

 

​        //查询框的实例化

​        JTextField jTextField = new JTextField();

​        jTextField.setPreferredSize(jLabelPrefSize);

 

​        //查询结构框的实例化

​        JTextField jTextField2 = new JTextField();

​        jTextField2.setPreferredSize(jLabelPrefSize);

​        jTextField2.setEnabled(false);

 

​        //格式化按钮的实例化

​        JButton jButton = new JButton();

​        jButton.setSize(10,21 );

​        jButton.setText("格式化");

​        jButton.addActionListener(new ActionListener() {

​            @Override

​            public void actionPerformed(ActionEvent e) {

​                String text = area1.getText();//获取用户输入的内容

​                area2.setText(convertText(text));//格式化以后，将结果输出到第二个文本域内

​            }

​        });

 

​        //查询按钮的实例化

​        JButton jButton2 = new JButton();

​        jButton2.setSize(10,21 );

​        jButton2.setText("查询");

​        jButton2.addActionListener(new ActionListener() {

​            @Override

​            public void actionPerformed(ActionEvent e) {

​                String text = area1.getText();//获取需要搜索的文本

​                String text2 = jTextField.getText();//获取需要查询的内容

​                String time = countString(text, text2);//获得结果

​                jTextField2.setText(time);//输出结果

​            }

​        });

 

​        //将控件添加到布局中

​        jf.setLayout(new FlowLayout());

​        jf.setBounds(x, y, width, height); //设置窗口的属性 窗口位置以及窗口的大小

​        jf.add(area1);

​        jf.add(jButton);

​        jf.add(area2);

​        jf.add(jLabel);

​        jf.add(jTextField);

​        jf.add(jButton2);

​        jf.add(jTextField2);

​        jf.setVisible(true);//设置窗口可见

​        jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //设置关闭方式 如果不设置的话 似乎关闭窗口之后不会退出程序

​    }

 

​    public static void main(String[] args) {

​        new JFrameWindow("古诗软件");        //创建窗口，启动窗口

​    }

 

​    private static String convertText(String context) {

 

​        StringBuilder after = new StringBuilder();//实例化字符串构造器

​        char[] chs = context.toCharArray();

​        //遍历字符数组保存到map

​        int i = 0;//作为一个循环停止开始的开关

​        for (Character ch : chs) {//历遍目标文字里面的所有字符

​            if (i == 6) {//当循环到第7个的时候，添加逗号

​                after.append(ch);

​                after.append(",");

​            } else if (i == 13) {//当循环到第14个的时候，添加句号

​                after.append(ch);

​                after.append("。");

​                after.append("\n");//添加换行符

​                i = -1;//重置开关

​            } else {

​                after.append(ch);//正常情况直接添加文字

​            }

​            i++;

​        }

​        outputFile(after.toString());//输出到文件里面

​        return after.toString();

​    }

 

​    //实现了重复字段的计算功能

​    private static String countString(String str, String s) {

​        int count = 0;

​        while (str.contains(s)) {//如果目标字符串包含了查询的字符，则加一

​            str = str.substring(str.indexOf(s) + 1, str.length());

​            count++;

​        }

​        return (s + "出现的次数为" + count + "次");

​    }

 

​    private static int outputFile(String content) {//输出到文件里面

​        String filePath = "古诗.txt";//在软件的同级目录下新建文件，此为路径

​        FileWriter fwriter = null;//实例化文件写入器

​        try {

​            fwriter = new FileWriter(filePath);//添加文件路径

​            fwriter.write(content);//在文件内写入内容

​        } catch (IOException ex) {//处理IO异常

​            ex.printStackTrace();

​        } finally {

​            try {

​                assert fwriter != null;

​                fwriter.flush();//清空一下

​                fwriter.close();//关闭文件读写器

​            } catch (IOException ex) {

​                ex.printStackTrace();//处理IO异常

​            }

​        }

​        return 0;

​    }

# **实验结果******

 

![JLfeKS.jpg](https://s1.ax1x.com/2020/04/30/JLfeKS.jpg)

# **实验收获******

通过这次实验，让我更深入的了解了异常处理，尤其是针对于try catch,对异常处理有了更深刻的认识。当然，在实验过程中有许许多多不懂还有不明白的地方，但通过查询相关资料以及询问同学已解决。

 

 
