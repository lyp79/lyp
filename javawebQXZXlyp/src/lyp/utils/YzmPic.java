package lyp.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class YzmPic {

	private final int width=60;//������ͼƬ���Ŀ�
	private final int height=20;//������ͼƬ���ĸ�

	private final int interfereLineNum = 88;//�����߸���
	private final int interferePointNum = 88;//�����߸���
	private final int interfereLineLenMax = 12;//����������������ֵ
	private final int authenticationCodeNumber = 4;//��֤���λ��
//	����ɫ�ķ�Χ
	private final int backgroundColorBegin =100;
	private final int backgroundColorFinal =250;

	private final int interfereColorBegin =160;
	private final int interfereColorFinal =200;

	private final int radomNumberColorBegin =20;
	private final int radomNumberColorFinal =130;
//	����
	private final String fontName="Times New Roman";
	private final int fontStyle = Font.PLAIN;
	private final int fontSize = 18;

	private final int stringWidth =13;//string�������
	private final int stringHight =16;//string�߶�����
	private final int stringClearance =6;//string���
//	rgb��Χ
	private final int rgbValue = 255;
	//
	private Graphics graphics;//�ڴ�������Ļ���

	public String authenticationCode=new String();//ȡ�����������֤��(4λ����),����Ľӿ�
	public BufferedImage image;//ͼƬ���ڴ�,����Ľӿ�

//	��֤���ɷ���������
	public YzmPic(){
		init();
	}
//	��֤��ͻ�������,rnΪ�ͻ������ɵ���֤��
	public YzmPic(String rn){
		init();
		authenticationCode = rn;
	}

	private void init(){
	image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	graphics = image.getGraphics();
	}
	public void drawServer(){
		//���������
		drawBasic();
		//�������߻��߸��ŵ�
		drawInterfereLine();
		//drawInterferePoint();
		//�������
		drawRadomNumberServer();
		//ͼ����Ч
		graphics.dispose();
	}
	public void drawClient(){
		//���������
		drawBasic();
		//�������߻��߸��ŵ�
		drawInterfereLine();
		
		drawInterferePoint();
		//�������
		drawRadomNumberClient();
		//ͼ����Ч
		graphics.dispose();
	}
//	 ������Χ��������ɫbc:begin color ,fc:finally color
	private Color getRandColor(int fc,int bc){
		Random random = new Random();
		if(fc>rgbValue) fc=rgbValue;
		if(bc>rgbValue) bc=rgbValue;
		int r=fc+random.nextInt(bc-fc);
		int g=fc+random.nextInt(bc-fc);
		int b=fc+random.nextInt(bc-fc);
		return new Color(r,g,b);
	}

//	�趨ͼƬ������Ϣ
	private void drawBasic(){
		//�趨����ɫ
		graphics.setColor(getRandColor(backgroundColorBegin,backgroundColorFinal));
		graphics.fillRect(0, 0, width, height);
		//�趨����
		graphics.setFont(new Font(fontName,fontStyle,fontSize));
		//���߿�
		graphics.setColor(new Color(rgbValue,rgbValue,rgbValue));
		graphics.drawRect(0,0,width-1,height-1);
	}
//	�������interfereLine�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
	private void drawInterfereLine(){
		Random random = new Random();
		graphics.setColor(getRandColor(interfereColorBegin,interfereColorFinal));
		for (int i=0;i<interfereLineNum;i++){
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(interfereLineLenMax);
		int yl = random.nextInt(interfereLineLenMax);
		graphics.drawLine(x,y,x+xl,y+yl);
	}
	}
//	 �������interfereLine�����ŵ㣬ʹͼ���е���֤�벻�ױ���������̽�⵽

	private void drawInterferePoint(){
		Random random = new Random();
		graphics.setColor(getRandColor(interfereColorBegin,interfereColorFinal));
		for (int i=0;i<interferePointNum;i++){
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		graphics.drawLine(x,y,x,y);
	}
	}
//	 ȡ�����������֤��(4λ����),�ɷ�����������
	private void drawRadomNumberServer(){
	Random random = new Random();
	for (int i=0;i<authenticationCodeNumber;i++){
	String rand=String.valueOf(random.nextInt(10));
	authenticationCode+=rand;
	graphics.setColor(getRandColor(radomNumberColorBegin,radomNumberColorFinal));
//	���ú�����������ɫ��ͬ����������Ϊ����̫�ӽ�������ֻ��ֱ������
	graphics.drawString(rand,stringWidth*i+stringClearance,stringHight);//����������Ϊ����
	}
//	System.out.println(authenticationCode);
	}
//	ȡ�����������֤��(4λ����),�ɿͻ�������
//	<script>
//	document.write("<img border=0 src=image.jsp?rand="+Math.random()*10000+">");
//	</script>
	private void drawRadomNumberClient(){
		authenticationCode = authenticationCode.substring(0,authenticationCode.indexOf("."));
		switch(authenticationCode.length()){
		      case 1: authenticationCode = "000"+authenticationCode; break;
		      case 2: authenticationCode = "00"+authenticationCode; break;
		      case 3: authenticationCode = "0"+authenticationCode; break;
		      default: authenticationCode = authenticationCode.substring(0,4); break;
		}
		graphics.setColor(getRandColor(radomNumberColorBegin,radomNumberColorFinal));
		graphics.drawString(authenticationCode,stringWidth,stringHight);
	}
}
