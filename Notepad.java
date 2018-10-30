import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

class FindDlg extends JDialog implements ActionListener
{
 JTextField tStr; 
 JButton bFind,bCancel;
 NotepadFrame nf;

 int i;
 String str;
 
 FindDlg(NotepadFrame nf)
 {
  super(nf,"Find",false);
  setSize(400,145);
  Point p=nf.getLocation();
  setLocation(p.x+300,p.y+100);

  this.nf=nf;

  NotepadPanel np=new NotepadPanel(25,10,10,10);
  add(np);

  tStr = new JTextField();
  tStr.setFont(new Font("lucida console",Font.PLAIN,18));
  
  bFind=new JButton("Find");
  bFind.setFont(new Font("lucida console",Font.PLAIN,18));
  bFind.setBackground(new Color(60,60,60));
  bFind.setForeground(Color.white);

  bCancel=new JButton("Cancel");
  bCancel.setFont(new Font("lucida console",Font.PLAIN,18));
  bCancel.setBackground(new Color(200,70,70));
  bCancel.setForeground(Color.white);

  JLabel l1=new JLabel("Find What ?");
  l1.setFont(new Font("lucida console",Font.PLAIN,18));
  l1.setForeground(Color.white);

  NotepadPanel p1=new NotepadPanel(1,1,1,1);
  p1.setLayout(new BorderLayout(10,10));
  p1.add(l1,BorderLayout.WEST);
  p1.add(tStr,BorderLayout.CENTER);
  
  NotepadPanel p2=new NotepadPanel(1,1,1,1);
  p2.setLayout(new GridLayout(1,3,25,10));
  p2.add(new JLabel());
  p2.add(bFind);
  p2.add(bCancel);
  
  np.setLayout(new GridLayout(2,1,10,10));
  np.add(p1);
  np.add(p2);

  str=nf.jta.getText();
  bFind.addActionListener(this);
  bCancel.addActionListener(this);
 }

 public void actionPerformed(ActionEvent ae)
 {
  if(ae.getSource()==bFind)
  {
   i=str.indexOf(tStr.getText(),i);

   if(i==-1)
   {
    JOptionPane.showMessageDialog(nf,"Search Complete..");
    i=0;
    bFind.setLabel("Find");
   }   
   else
   {
    bFind.setLabel("Find Next");
    nf.jta.select(i,tStr.getText().length()+i);
    nf.jta.requestFocus();
    i=i+tStr.getText().length();
   }
  }
  else
  if(ae.getSource()==bCancel)
  {
   dispose();
  }
 }
}

class ReplaceDlg extends JDialog implements ActionListener
{
 JTextField tOld,tNew; 
 JButton bFind,bCancel,bReplace,bReplaceAll;
 NotepadFrame nf;

 int i;
 String str;
 
 ReplaceDlg(NotepadFrame nf)
 {
  super(nf,"Replace",false);
  setSize(750,170);
  Point p=nf.getLocation();
  setLocation(p.x+300,p.y+100);

  this.nf=nf;

  NotepadPanel np=new NotepadPanel(25,10,10,10);
  add(np);

  tOld = new JTextField();
  tOld.setFont(new Font("lucida console",Font.PLAIN,18));

  tNew = new JTextField();
  tNew.setFont(new Font("lucida console",Font.PLAIN,18));
  
  bFind=new JButton("Find");
  bFind.setFont(new Font("lucida console",Font.PLAIN,18));
  bFind.setBackground(new Color(60,60,60));
  bFind.setForeground(Color.white);

  bReplace=new JButton("Replace");
  bReplace.setEnabled(false);
  bReplace.setFont(new Font("lucida console",Font.PLAIN,18));
  bReplace.setBackground(new Color(60,60,60));
  bReplace.setForeground(Color.white);

  bReplaceAll=new JButton("Replace All");
  bReplaceAll.setEnabled(false);
  bReplaceAll.setFont(new Font("lucida console",Font.PLAIN,18));
  bReplaceAll.setBackground(new Color(60,60,60));
  bReplaceAll.setForeground(Color.white);

  bCancel=new JButton("Cancel");
  bCancel.setFont(new Font("lucida console",Font.PLAIN,18));
  bCancel.setBackground(new Color(200,70,70));
  bCancel.setForeground(Color.white);

  JLabel l1=new JLabel("Replace What ?");
  l1.setFont(new Font("lucida console",Font.PLAIN,18));
  l1.setForeground(Color.white);

  JLabel l2=new JLabel("Replace With :");
  l2.setFont(new Font("lucida console",Font.PLAIN,18));
  l2.setForeground(Color.white);  

  NotepadPanel p1=new NotepadPanel(1,1,1,1);
  p1.setLayout(new BorderLayout(10,10));
  p1.add(l1,BorderLayout.WEST);
  p1.add(tOld,BorderLayout.CENTER);
  
  NotepadPanel p2=new NotepadPanel(1,1,1,1);
  p2.setLayout(new BorderLayout(10,10));
  p2.add(l2,BorderLayout.WEST);
  p2.add(tNew,BorderLayout.CENTER);

  NotepadPanel p3=new NotepadPanel(1,1,1,1);
  p3.setLayout(new GridLayout(1,3,25,10));
  p3.add(bFind);
  p3.add(bReplace);
  p3.add(bReplaceAll);
  p3.add(bCancel);
  
  np.setLayout(new GridLayout(3,1,10,10));
  np.add(p1);
  np.add(p2);
  np.add(p3);

  str=nf.jta.getText();
  bFind.addActionListener(this);
  bReplace.addActionListener(this);
  bReplaceAll.addActionListener(this);
  bCancel.addActionListener(this);
 }

 public void actionPerformed(ActionEvent ae)
 {
  if(ae.getSource()==bFind)
  {
   String ms=nf.jta.getText();
   String ss=tOld.getText();
   i=ms.indexOf(ss,i);
   
   if(i==-1)
   {
    JOptionPane.showMessageDialog(this,"Search Complete..");
    i=0;
    bFind.setLabel("Find");
   }   
   else
   {
    bReplace.setEnabled(true);
    bReplaceAll.setEnabled(true);
    
    nf.jta.select(i,ss.length()+i);
    nf.jta.requestFocus();
    bFind.setLabel("Find Next");
    i=i+ss.length();
   }
  }
  else
  if(ae.getSource()==bReplace)
  {
   String ms=nf.jta.getText();
   String ss=tOld.getText();
   String rs=tNew.getText();
   i=i-ss.length();

   StringBuffer sb=new StringBuffer(ms);
   sb.replace(i,i+ss.length(),rs);
   i=i+rs.length();
   nf.jta.setText(sb.toString());
   actionPerformed(new ActionEvent(bFind,0,"Find Next"));
  }
  else
  if(ae.getSource()==bReplaceAll)
  {
   String ms=nf.jta.getText();
   String ss=tOld.getText();
   String rs=tNew.getText();
   i=i-ss.length();

   String s=ms.substring(i);
   s=s.replace(ss,rs);
   s=ms.substring(0,i)+s;
   nf.jta.setText(s);

   bFind.setText("Find");
   bReplace.setEnabled(false);
   bReplaceAll.setEnabled(false);
   i=0;
  }
  else
  if(ae.getSource()==bCancel)
  {
   dispose();
  }
 }
}

class GotoDlg extends JDialog implements ActionListener
{
 NotepadFrame nf;
 JSpinner js;
 JButton bGo;
 
 GotoDlg(NotepadFrame nf)
 {
  super(nf,"Goto");
  this.nf=nf;
  setSize(450,100);
  Point pt=nf.getLocation();
  setLocation(pt.x+300,pt.y+200);

  NotepadPanel np=new NotepadPanel(15,15,15,15);
  add(np);
  
  JLabel l=new JLabel("Line no.");
  l.setFont(new Font("lucida console",Font.PLAIN,18));
  l.setForeground(Color.white);

  js=new JSpinner(new SpinnerNumberModel(1,1,nf.jta.getLineCount(),1));

  bGo=new JButton("Go");
  bGo.setFont(new Font("lucida console",Font.PLAIN,18));
  bGo.setBackground(new Color(150,70,70));
  bGo.setForeground(Color.white);
  
  np.setLayout(new GridLayout(1,2,10,10));
  np.add(l);
  np.add(js);
  np.add(new JLabel());
  np.add(bGo);

  bGo.addActionListener(this);
 }

 public void actionPerformed(ActionEvent ae)
 {
  if(ae.getSource()==bGo)
  {
   int n=(Integer)js.getValue()-1;
   try
   {
    nf.jta.setCaretPosition(nf.jta.getLineStartOffset(n));
   }
   catch(javax.swing.text.BadLocationException e){}
   dispose();
  }
 }
}

class FontDlg extends JDialog implements ListSelectionListener,ActionListener,ChangeListener
{
 JList lst;
 JRadioButton bPlain,bBold;
 JCheckBox bItalic;
 JSpinner spn;
 JButton bApply,bCancel;
 JTextField tSample;
 NotepadFrame nf;

 String font;
 int efx;
 int size;
 
 FontDlg(NotepadFrame nf)
 {
  super(nf,"Font");
  setSize(400,250);
  Point pt=nf.getLocation();
  setLocation(pt.x+300,pt.y+150);

  font=nf.font;
  efx=nf.efx;
  size=nf.size;

  GraphicsEnvironment ge;
  ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
  
  lst=new JList(ge.getAvailableFontFamilyNames());
  lst.setFont(new Font("arial",Font.PLAIN,12));
  JScrollPane jsp=new JScrollPane(lst);
  jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

  this.nf=nf;
  
  NotepadPanel np=new NotepadPanel(10,10,10,10);
  add(np);

  bPlain=new JRadioButton("Plain");
  bPlain.setFont(new Font("arial",Font.PLAIN,12));
  bPlain.setForeground(Color.white);
  bPlain.setBackground(new Color(100,100,100));
    
  bBold=new JRadioButton("Bold");
  bBold.setFont(new Font("arial",Font.PLAIN,12));
  bBold.setForeground(Color.white);
  bBold.setBackground(new Color(100,100,100));

  ButtonGroup bg=new ButtonGroup();
  bg.add(bPlain);
  bg.add(bBold);
     
  bItalic=new JCheckBox("Italic");
  bItalic.setFont(new Font("lucida console",Font.PLAIN,12));
  bItalic.setForeground(Color.white);
  bItalic.setBackground(new Color(100,100,100));

  JLabel lbl=new JLabel("size");
  lbl.setFont(new Font("lucida console",Font.PLAIN,18));
  lbl.setForeground(Color.white);

  spn=new JSpinner(new SpinnerNumberModel(12,12,72,1));

  bApply=new JButton("Apply");
  bApply.setFont(new Font("lucida console",Font.PLAIN,18));
  bApply.setForeground(Color.white);
  bApply.setBackground(new Color(170,70,70));

  bCancel=new JButton("Cancel");
  bCancel.setFont(new Font("lucida console",Font.PLAIN,18));
  bCancel.setForeground(Color.white);
  bCancel.setBackground(new Color(70,170,70));
 
  tSample=new JTextField("AaBbGgFfYy");
  tSample.setFont(new Font("lucida console",Font.PLAIN,12));

  np.setLayout(new GridLayout(2,1,10,10));
  
  NotepadPanel p1=new NotepadPanel(1,1,1,1);
  p1.setLayout(new GridLayout(1,2,10,10));

  NotepadPanel p11=new NotepadPanel(1,1,1,1);
  p11.setLayout(new GridLayout(4,1,5,5));

   NotepadPanel p111=new NotepadPanel(1,1,1,1);
   p111.setLayout(new GridLayout(1,2,5,5));
   p111.add(lbl);
   p111.add(spn);

  p11.add(bPlain);
  p11.add(bBold);
  p11.add(bItalic);
  p11.add(p111);

  p1.add(jsp);
  p1.add(p11);

  NotepadPanel p2=new NotepadPanel(1,1,1,1);
  p2.setLayout(new GridLayout(1,2,10,10));
   
  NotepadPanel p21=new NotepadPanel(5,5,5,5);
  p21.setBorder(BorderFactory.createLineBorder(Color.red,1));
  p21.setLayout(new BorderLayout(5,5));
  p21.add(tSample,BorderLayout.CENTER);

  NotepadPanel p22=new NotepadPanel(5,5,5,5);
  p22.setLayout(new GridLayout(2,1,10,10));
  p22.add(bApply);
  p22.add(bCancel);
  
  p2.add(p21);
  p2.add(p22);

  np.add(p1);
  np.add(p2);

  lst.addListSelectionListener(this);

  bPlain.addActionListener(this);
  bBold.addActionListener(this);
  bItalic.addActionListener(this);
  bApply.addActionListener(this);
  bCancel.addActionListener(this);

  spn.addChangeListener(this);
 }

 public void stateChanged(ChangeEvent ce)
 {
  size=(Integer)spn.getValue();
  tSample.setFont(new Font(font,efx,size));
 }

 public void valueChanged(ListSelectionEvent le)
 {
  font=(String)lst.getSelectedValue();
  efx=0;
  if(bBold.isSelected())
   efx=1;
  if(bItalic.isSelected())
   efx=efx+2;

  tSample.setFont(new Font(font,efx,size));
 }

 public void actionPerformed(ActionEvent ae)
 {
  if(ae.getSource()==bApply)
  {
   nf.font=font;
   nf.efx=efx;
   nf.size=size;
   nf.jta.setFont(new Font(font,efx,size));
   dispose();
  }
  else
  if(ae.getSource()==bCancel)
  {
   dispose();
  }
  else
  {
   efx=0;
   if(bBold.isSelected())
    efx=1;
   
   if(bItalic.isSelected())
    efx=efx+2;

   tSample.setFont(new Font(font,efx,size));
  }
 }
}

class NotepadFrame extends JFrame implements ActionListener
{
 JMenuItem mNew,mOpn,mSav,mSas,mCls;
 JMenuItem mGoto,mFind,mReplace;
 JMenuItem mFont,mColor;
 JMenuItem mExit;
 JTextArea jta;
 
 File file;
 String str="";

 Color fontColor=Color.black;

 String font="lucida console";
 int efx=0;
 int size=20;

 NotepadFrame()
 { 
  super("Notepad - Untitled");
  setSize(900,700);
  setLocation(120,100);

  jta=new JTextArea();
  jta.setFont(new Font("lucida console",Font.PLAIN,20));

  JScrollPane jsp=new JScrollPane(jta);
  jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

  mNew=new JMenuItem("New");
  mNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,Event.CTRL_MASK));
  mOpn=new JMenuItem("Open");
  mOpn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,Event.CTRL_MASK));
  mSav=new JMenuItem("Save");
  mSav.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,Event.CTRL_MASK));
  mSas=new JMenuItem("Save as");
  mCls=new JMenuItem("Close");
  mCls.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,Event.CTRL_MASK));

  mGoto=new JMenuItem("Goto");
  mGoto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,Event.CTRL_MASK));
  mFind=new JMenuItem("Find");
  mFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,Event.CTRL_MASK));
  mReplace=new JMenuItem("Replace");
  mReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,Event.CTRL_MASK));

  mFont=new JMenuItem("Font");
  mColor=new JMenuItem("Color");

  mExit=new JMenuItem("Exit");
  mExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,Event.CTRL_MASK));

  JMenu mFile=new JMenu("File");
  mFile.setMnemonic(KeyEvent.VK_F); // virtual key
  mFile.add(mNew);
  mFile.add(mOpn);
  mFile.add(mSav);
  mFile.add(mSas);
  mFile.add(mCls);
  mFile.addSeparator();
  mFile.add(mExit);
  
  JMenu mEdit=new JMenu("Edit");
  mEdit.setMnemonic(KeyEvent.VK_E); // virtual key

  mEdit.add(mGoto);
  mEdit.add(mFind);
  mEdit.add(mReplace);

  JMenu mFormat=new JMenu("Format");
  mFormat.setMnemonic(KeyEvent.VK_O); // virtual key

  mFormat.add(mFont);
  mFormat.add(mColor);

  JMenuBar mb=new JMenuBar();
  mb.add(mFile);
  mb.add(mEdit);
  mb.add(mFormat);
  
  setJMenuBar(mb);

  NotepadPanel np=new NotepadPanel(1,1,1,1);
  np.setLayout(new BorderLayout(1,1));
  np.add(jsp,BorderLayout.CENTER);
  add(np);
  
  mNew.addActionListener(this);
  mOpn.addActionListener(this);
  mSav.addActionListener(this);
  mSas.addActionListener(this); 
  mCls.addActionListener(this);
  mExit.addActionListener(this);

  mGoto.addActionListener(this);
  mFind.addActionListener(this); 
  mReplace.addActionListener(this);

  mFont.addActionListener(this);
  mColor.addActionListener(this);

 }

 public boolean saveAs()
 {
  JFileChooser jfc = new JFileChooser("d:/jprogs/SwingApps");
  jfc.setDialogTitle("Save File As");
  int opt = jfc.showSaveDialog(this);

  if(opt == JFileChooser.CANCEL_OPTION)
  {
   return false; 
  }
  file = jfc.getSelectedFile();

  try
  {
   FileWriter fw = new FileWriter(file);
   BufferedWriter bw = new BufferedWriter(fw);

   bw.write(jta.getText());
   bw.flush();
   fw.close();
  }
  catch(IOException e)
  {
   JOptionPane.showMessageDialog(this,"i/o alert - "+e.getMessage());
  }
  return true;
 }

 public void actionPerformed(ActionEvent ae)
 {
  if(ae.getSource()==mExit)
  {
   System.exit(0);
  }
  else
  if(ae.getSource()==mNew)
  {
   if(!jta.getText().equals(str))
   {
    int opt = JOptionPane.showConfirmDialog(this,"Save Changes to File ?","Notepad",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
    if(opt == JOptionPane.CANCEL_OPTION)
    {
     return;
    }
    else
    if(opt== JOptionPane.YES_OPTION)
    {
     if(file==null)
     {
      if(!saveAs())
       return;
     }
     else
     {
      try
      {
       FileWriter fw = new FileWriter(file); 
       BufferedWriter bw = new BufferedWriter(fw);

       bw.write(jta.getText());
       bw.flush();
       fw.close();
      }
      catch(IOException e)
      {
       JOptionPane.showMessageDialog(this,"i/o alert - "+e.getMessage());
      }
     }
    }
   } 
   str="";
   file=null;
   jta.setText("");
   setTitle("Notepad - Untitled"); 
  }
  else
  if(ae.getSource()==mOpn)
  {
   JFileChooser jfc = new JFileChooser("d:/jprogs/SwingApps");
   jfc.setDialogTitle("Select Title to open");
   int opt = jfc.showOpenDialog(this);

   if(opt == JFileChooser.CANCEL_OPTION)
   {
    return;
   }
   file = jfc.getSelectedFile();
   setTitle("Notepad - "+file.getName());
  
   try
   {
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);

    str="";
    String s = br.readLine();
    while(s!=null)
    {
     str=str+s+"\n";
     s=br.readLine();
    }
    fr.close();
    jta.setText(str);
    jta.setCaretPosition(0);
   }
   catch(IOException e)
   {
    JOptionPane.showMessageDialog(this,"i/o alert - "+e.getMessage());
   }
  }
  else
  if(ae.getSource()==mSav)
  {
   if(file==null)
   {
    if(!saveAs())
     return;
   }
   try
   {
    FileWriter fw = new FileWriter(file); 
    BufferedWriter bw = new BufferedWriter(fw);

    bw.write(jta.getText());
    bw.flush();
    fw.close();
   }
   catch(IOException e)
   {
    JOptionPane.showMessageDialog(this,"i/o alert - "+e.getMessage());
    return;
   }
   str=jta.getText();
   setTitle("Notepad - "+file.getName());
  }
  else
  if(ae.getSource()==mSas)
  {
   if(!saveAs()) return;
  }
  else
  if(ae.getSource()==mCls)
  {
   str="";
   file=null;
   jta.setText("");
   setTitle("Notepad - Untitled");
  }
  else
  if(ae.getSource()==mGoto)
  {
   GotoDlg gd=new GotoDlg(this);
   gd.setVisible(true);
  }
  else
  if(ae.getSource()==mFind)
  {
   FindDlg fd=new FindDlg(this);
   fd.setVisible(true);
  }
  else
  if(ae.getSource()==mReplace)
  {
   ReplaceDlg rd=new ReplaceDlg(this);
   rd.setVisible(true);
  }
  else
  if(ae.getSource()==mFont)
  {
   FontDlg fd=new FontDlg(this);
   fd.setVisible(true);
  }
  else
  if(ae.getSource()==mColor)
  {
   Color clr = JColorChooser.showDialog(this,"select Color",fontColor);
   if(clr!=null)
   {
    fontColor=clr;
    jta.setForeground(fontColor);
   }
  }
 }
}

class NotepadPanel extends JPanel
{
 int top,left,bottom,right;

 NotepadPanel(int top,int left,int bottom,int right)
 {
  this.top=top;
  this.left=left;
  this.right=right;
  this.bottom=bottom;
 }

 public Insets getInsets()
 {
  return new Insets(top,left,bottom,right);
 }

 public void paintComponent(Graphics g)
 {
  super.paintComponent(g);
  setBackground(new Color(100,100,100));
 }
}

class Notepad
{
 public static void main(String args[])
 {
  NotepadFrame nf=new NotepadFrame();
  nf.setVisible(true);
  nf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
}