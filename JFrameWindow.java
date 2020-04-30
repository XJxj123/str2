import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class JFrameWindow extends JFrame {     //需要继承JFrame
    public JFrameWindow(String title) {
        JFrame jf = new JFrame(title);
        Container container = jf.getContentPane();    //得到窗口的容器

        //获取当前屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //自定义一些窗口控件的长宽高
        Dimension newPrefSize = new Dimension(400,200);
        Dimension jLabelPrefSize = new Dimension(400,20);
        int width = 450;
        int height = 800;
        //将窗口设置在屏幕的正中心
        int x = ((int) screenSize.getWidth() - width) / 2;
        int y = ((int) screenSize.getHeight() - height) / 2;

        //第一个文本域的实例化
        JTextArea area1 = new JTextArea(10,20);
        area1.setPreferredSize(newPrefSize);
        area1.setLineWrap(true);

        //第二个结果文本域的实例化
        JTextArea area2 = new JTextArea(10,20);
        area2.setPreferredSize(newPrefSize);
        area2.setLineWrap(true);
        area2.setEnabled(false);//设置不可以改变的属性

        //标签提示的实例化
        JLabel jLabel = new JLabel();
        jLabel.setPreferredSize(jLabelPrefSize);
        jLabel.setText("查询重复字段：");

        //查询框的实例化
        JTextField jTextField = new JTextField();
        jTextField.setPreferredSize(jLabelPrefSize);

        //查询结构框的实例化
        JTextField jTextField2 = new JTextField();
        jTextField2.setPreferredSize(jLabelPrefSize);
        jTextField2.setEnabled(false);

        //格式化按钮的实例化
        JButton jButton = new JButton();
        jButton.setSize(10,21 );
        jButton.setText("格式化");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = area1.getText();//获取用户输入的内容
                area2.setText(convertText(text));//格式化以后，将结果输出到第二个文本域内
            }
        });

        //查询按钮的实例化
        JButton jButton2 = new JButton();
        jButton2.setSize(10,21 );
        jButton2.setText("查询");
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = area1.getText();//获取需要搜索的文本
                String text2 = jTextField.getText();//获取需要查询的内容
                String time = countString(text, text2);//获得结果
                jTextField2.setText(time);//输出结果
            }
        });

        //将控件添加到布局中
        jf.setLayout(new FlowLayout());
        jf.setBounds(x, y, width, height); //设置窗口的属性 窗口位置以及窗口的大小
        jf.add(area1);
        jf.add(jButton);
        jf.add(area2);
        jf.add(jLabel);
        jf.add(jTextField);
        jf.add(jButton2);
        jf.add(jTextField2);
        jf.setVisible(true);//设置窗口可见
        jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //设置关闭方式 如果不设置的话 似乎关闭窗口之后不会退出程序
    }

    public static void main(String[] args) {
        new JFrameWindow("古诗软件");        //创建窗口，启动窗口
    }

    private static String convertText(String context) {

        StringBuilder after = new StringBuilder();//实例化字符串构造器
        char[] chs = context.toCharArray();
        //遍历字符数组保存到map
        int i = 0;//作为一个循环停止开始的开关
        for (Character ch : chs) {//历遍目标文字里面的所有字符
            if (i == 6) {//当循环到第7个的时候，添加逗号
                after.append(ch);
                after.append(",");
            } else if (i == 13) {//当循环到第14个的时候，添加句号
                after.append(ch);
                after.append("。");
                after.append("\n");//添加换行符
                i = -1;//重置开关
            } else {
                after.append(ch);//正常情况直接添加文字
            }
            i++;
        }
        outputFile(after.toString());//输出到文件里面
        return after.toString();
    }

    //实现了重复字段的计算功能
    private static String countString(String str, String s) {
        int count = 0;
        while (str.contains(s)) {//如果目标字符串包含了查询的字符，则加一
            str = str.substring(str.indexOf(s) + 1, str.length());
            count++;
        }
        return (s + "出现的次数为" + count + "次");
    }

    private static int outputFile(String content) {//输出到文件里面
        String filePath = "古诗.txt";//在软件的同级目录下新建文件，此为路径
        FileWriter fwriter = null;//实例化文件写入器
        try {
            fwriter = new FileWriter(filePath);//添加文件路径
            fwriter.write(content);//在文件内写入内容
        } catch (IOException ex) {//处理IO异常
            ex.printStackTrace();
        } finally {
            try {
                assert fwriter != null;
                fwriter.flush();//清空一下
                fwriter.close();//关闭文件读写器
            } catch (IOException ex) {
                ex.printStackTrace();//处理IO异常
            }
        }
        return 0;
    }


}