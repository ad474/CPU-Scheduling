
//NAME= Ankita Durgavajula
//ROLL NUMBER= 1710110057

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ankita
 */

public class cpuscheduling extends JPanel implements ActionListener{
    JFrame f1=new JFrame();
    static JFrame f2=new JFrame();
    JLabel l1=new JLabel("Number of processors (<=6)");
    String[] options={"P1","P2","P3","P4","P5","P6"};
    JComboBox cb1=new JComboBox(options);
    JLabel l2=new JLabel("Burst time");
    JTextField tf1=new JTextField();
    JLabel l3=new JLabel("Arrival time");
    JTextField tf2=new JTextField();
    JLabel l4=new JLabel("Priority");
    JTextField tf3=new JTextField();
    JLabel l5=new JLabel("Time Quantum");
    JTextField tf4=new JTextField();
    JCheckBox rb1=new JCheckBox("FCFS");
    JCheckBox rb2=new JCheckBox("Round Robin");
    JCheckBox rb3=new JCheckBox("Preemptive SJF");
    JCheckBox rb4=new JCheckBox("Non preemptive SJF");
    JCheckBox rb5=new JCheckBox("Preemptive priority");
    JCheckBox rb6=new JCheckBox("Non preemptive priority");
    JButton button=new JButton("COMPUTE");
    int flag1=0,flag2=0,flag3=0,flag4=0,flag5=0,flag6=0;
    String[] bt=new String[6];
    String[] at=new String[6];
    String[] priority=new String[6];
    String[] procname=new String[6];
    int[] ct=new int[6];
    int[] tat=new int[6];
    int[] wat=new int[6];
    String timeq;
    int n=0;
    int pn=0;
    String[] tempat=new String[6];
    String[] tempbt=new String[6];
    int tq;
    rrdatatype[] rr=new rrdatatype[40];
    int rrn=0;
    ArrayList<sjfdatatype> mlist=new ArrayList<sjfdatatype>();
    ArrayList<rrdatatype> psjf=new ArrayList<rrdatatype>();
    public cpuscheduling(){
        f1.setLayout(new GridLayout(2,1));
        JPanel tp1=new JPanel();
        JPanel tp2=new JPanel();
        JPanel tp3=new JPanel();
        tp1.setLayout(new GridLayout(5,2));
        tp1.add(l1);
        tp1.add(cb1);
        tp1.add(l2);
        tp1.add(tf1);
        tp1.add(l3);
        tp1.add(tf2);
        tp1.add(l4);
        tp1.add(tf3);
        tp1.add(l5);
        tp1.add(tf4);
        tp3.setLayout(new GridLayout(3,2));
        tp3.add(rb1);
        tp3.add(rb2);
        tp3.add(rb3);
        tp3.add(rb4);
        tp3.add(rb5);
        tp3.add(rb6);
        rb1.addActionListener(this);
        rb2.addActionListener(this);
        rb3.addActionListener(this);
        rb4.addActionListener(this);
        rb5.addActionListener(this);
        rb6.addActionListener(this);
        tp2.setLayout(new BorderLayout());
        tp2.add(tp3,BorderLayout.CENTER);
        tp2.add(button,BorderLayout.SOUTH);
        button.addActionListener(this);
        tp1.setVisible(true);
        tp2.setVisible(true);
        tp3.setVisible(true);
        f1.add(tp1);
        f1.add(tp2);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(500, 500);
        f1.setVisible(true);
    }
    void extractBT(){
        n=0;
        String ts1=tf1.getText();
        String temp = "";
        for(int i=0;i<ts1.length();i++){
            if(i==ts1.length()-1){
                temp+=ts1.charAt(i);
                bt[n++]=temp;
                temp="";
                break;
            }
            if(ts1.charAt(i)==','){
                bt[n++]=temp;
                temp="";
            }
            else
                temp+=ts1.charAt(i);
        }
    }
    void extractAT(){
        String temp="";
        n=0;
        temp="";
        String ts2=tf2.getText();
        for(int i=0;i<ts2.length();i++){
            if(i==ts2.length()-1){
                temp+=ts2.charAt(i);
                at[n++]=temp;
                temp="";
                break;
            }
            if(ts2.charAt(i)==','){
                at[n++]=temp;
                temp="";
            }
            else
                temp+=ts2.charAt(i);
        }
        
    }
    void extractPriority(){
        String temp;
        n=0;
        temp="";
        String ts3=tf3.getText();
        for(int i=0;i<ts3.length();i++){
            if(i==ts3.length()-1){
                temp+=ts3.charAt(i);
                priority[n++]=temp;
                temp="";
                break;
            }
            if(ts3.charAt(i)==','){
                priority[n++]=temp;
                temp="";
            }
            else
                temp+=ts3.charAt(i);
        }
    }
    void getTQ(){
        timeq=tf4.getText();
        tq=Integer.parseInt(timeq);
    }
    void extractPN(){
        String t=cb1.getSelectedItem().toString().charAt(1)+"";
        pn=Integer.parseInt(t);
    }
    void extractProcname(){
        for(int i=0;i<pn;i++){
            procname[i]="P"+(i+1);
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("FCFS")){
            if(flag1==0)
                flag1=1;
            else
                flag1=0;
        }
        if(ae.getActionCommand().equals("Round Robin")){
            if(flag2==0)
                flag2=1;
            else
                flag2=0;
        }
        if(ae.getActionCommand().equals("Preemptive SJF")){
            if(flag3==0)
                flag3=1;
            else
                flag3=0;
        }
        if(ae.getActionCommand().equals("Non preemptive SJF")){
            if(flag4==0)
                flag4=1;
            else
                flag4=0;
        }
        if(ae.getActionCommand().equals("Preemptive priority")){
            if(flag5==0)
                flag5=1;
            else
                flag5=0;
        }
        if(ae.getActionCommand().equals("Non preemptive priority")){
            if(flag6==0)
                flag6=1;
            else
                flag6=0;
        }
        if(ae.getActionCommand().equals("COMPUTE")){
            repaint();
            f2.setVisible(true);
        }
    }
    public void paintComponent(Graphics g){
        if(flag1==1){
            extractPN();
            extractAT();
            extractBT();
            extractProcname();
            FCFS();  
            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
            Font gt=newFont.deriveFont(Font.BOLD);
            g.setFont(gt);
            g.drawString("FCFS", 10, 15);
            g.setFont(currentFont);
            g.drawRect(50,20,370, 40);
            for(int i=0;i<pn;i++){
                int pos=370/ct[pn-1]*ct[i];
                if(i!=pn-1)
                g.drawLine(50+pos, 20, 50+pos, 60);
                g.drawString(ct[i]+"", 50+pos, 73);
                if(i==0){
                    int textpos=(370/ct[pn-1]*ct[0])/2;
                    g.drawString(procname[i], 50+textpos, 45);
                }
                else{
                    int textpos=370/ct[pn-1]*((ct[i]-ct[i-1])/2+ct[i-1]);
                    g.drawString(procname[i], 50+textpos, 45);
                }
            }
            int s=0;
            String wt="Waiting time: ";
            for(int i=0;i<pn;i++){
                String pname="P"+(i+1);
                wt+=(pname+"=");
                for(int j=0;j<pn;j++){
                    if(pname.equals(procname[j])){
                        wt+=wat[j];
                        s+=wat[j];
                        if(i!=pn-1)
                            wt+=", ";
                        else
                            wt+=" ";
                    }
                }
            }
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            wt+="      Average WT="+df.format((double)s/pn);
            g.drawString(wt, 5, 90);
            s=0;
            String tt="Turn around time: ";
            for(int i=0;i<pn;i++){
                String pname="P"+(i+1);
                tt+=(pname+"=");
                for(int j=0;j<pn;j++){
                    if(pname.equals(procname[j])){
                        tt+=tat[j];
                        s+=tat[j];
                        if(i!=pn-1)
                            tt+=", ";
                        else
                            tt+=" ";
                    }
                }
            }
            
            tt+="      Average TAT="+df.format((double)s/pn);
            g.drawString(tt, 5,110);
        }
        if(flag2==1){
            extractPN();
            extractAT();
            extractBT();
            extractProcname();
            getTQ();
            roundRobin();
            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
            Font gt=newFont.deriveFont(Font.BOLD);
            g.setFont(gt);
            g.drawString("Round Robin", 10, 130);
            g.setFont(currentFont);
            g.drawRect(50,135,370, 40);
            int sum=0,pos=0,bn=0,textpos=0;
            for(int i=0;i<pn;i++){
                ct[i]=0;
                wat[i]=0;
                tat[i]=0;
            }
            for(int i=0;i<pn;i++)
                sum+=Integer.parseInt(bt[i]);
            for(int i=0;i<rrn;i++){
                pos+=370/sum*rr[i].time;
                if(i!=rrn-1)
                    g.drawLine(50+pos, 135, 50+pos, 175);
                bn+=rr[i].time;
                g.drawString(bn+"", 50+pos, 188);
                if(i==0){
                    g.drawString(rr[i].proc, 53, 160);
                    textpos=pos;
                }
                else{
                    g.drawString(rr[i].proc,53+textpos , 160);
                    textpos=pos;
                }
            }
            int[] procpos=new int[pn];
            for(int i=0;i<pn;i++){
                for(int j=0;j<rrn;j++){
                    if(rr[j].proc.equals("P"+(i+1)))
                        procpos[i]=j;
                }
            }
            for(int i=0;i<pn;i++)
                for(int j=0;j<procpos[i]+1;j++)
                    ct[i]+=rr[j].time;
            for(int i=0;i<pn;i++){
                wat[i]=0;
                tat[i]=0;
            }
            for(int i=0;i<pn;i++){
                tat[i]=ct[i]-Integer.parseInt(at[i]);
                wat[i]=tat[i]-Integer.parseInt(bt[i]);
            }
            int s=0;
            String wt="Waiting time: ";
            for(int i=0;i<pn;i++){
                String pname="P"+(i+1);
                wt+=(pname+"=");
                for(int j=0;j<pn;j++){
                    if(pname.equals(procname[j])){
                        wt+=wat[j];
                        s+=wat[j];
                        if(i!=pn-1)
                            wt+=", ";
                        else
                            wt+=" ";
                    }
                }
            }
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            wt+="     Average WT="+df.format((double)s/pn);
            g.drawString(wt, 5, 205);
            s=0;
            String tt="Turn around time: ";
            for(int i=0;i<pn;i++){
                String pname="P"+(i+1);
                tt+=(pname+"=");
                for(int j=0;j<pn;j++){
                    if(pname.equals(procname[j])){
                        tt+=tat[j];
                        s+=tat[j];
                        if(i!=pn-1)
                            tt+=", ";
                        else
                            tt+=" ";
                    }
                }
            }
            tt+="      Average TAT="+df.format((double)s/pn);
            g.drawString(tt, 5,225);
        }
        if(flag3==1){
            extractPN();
            extractAT();
            extractBT();
            extractProcname();
            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
            Font gt=newFont.deriveFont(Font.BOLD);
            g.setFont(gt);
            g.drawString("Preemptive SJF", 10, 245);
            g.setFont(currentFont);
            
            g.drawRect(50,250,370, 40);
            pSJF();
            int tsum=0;
            for (int i = 0; i < psjf.size(); i++) {
                tsum+=psjf.get(i).time;
            }
            for(int i=0;i<pn;i++){
                ct[i]=0;
                wat[i]=0;
                tat[i]=0;
            }
            int pos=0,c=0,textpos=0;
            for(int i=0;i<psjf.size();i++){
                pos+=370/tsum*psjf.get(i).time;
                c+=psjf.get(i).time;
                if(i!=psjf.size()-1)
                    g.drawLine(50+pos, 250, 50+pos, 290);
                g.drawString(c+"", 50+pos, 303);
                if(i==0){
                    g.drawString(psjf.get(i).proc, 53, 275);
                    textpos=pos;
                }
                else{
                    g.drawString(psjf.get(i).proc,53+textpos , 275);
                    textpos=pos;
                }
            }
            for (int i = 0; i < psjf.size(); i++) {
                int csum=0;
                int x=Integer.parseInt(psjf.get(i).proc.charAt(1)+"")-1;
                for (int j = 0; j <=i; j++) {
                    csum+=psjf.get(j).time;
                }
                ct[x]=csum;
            }
            int tatsum=0;
            int wsum=0;
            for (int i = 0; i < pn; i++) {
                tat[i]=ct[i]-Integer.parseInt(at[i]);
                tatsum+=tat[i];
                wat[i]=tat[i]-Integer.parseInt(bt[i]);
                wsum+=wat[i];
            }
            String tt="Turnaround time: ";
            for (int i = 0; i < pn; i++) {
                tt+=procname[i];
                tt+="=";
                tt+=(tat[i]+"");
                if(i!=pn-1)
                    tt+=", ";
                else
                    tt+=" ";
            }
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            tt+="      Average TAT="+df.format((double)tatsum/pn);
            g.drawString(tt, 5, 315);
            tt="Waiting time: ";
            for (int i = 0; i < pn; i++) {
                tt+=procname[i];
                tt+="=";
                tt+=(wat[i]+"");
                if(i!=pn-1)
                    tt+=", ";
                
            }
            tt+="      Average WT="+df.format((double)wsum/pn);
            g.drawString(tt, 5, 330);
        }
        if(flag4==1){
            extractPN();
            extractAT();
            extractBT();
            extractProcname();
            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
            Font gt=newFont.deriveFont(Font.BOLD);
            g.setFont(gt);
            g.drawString("Non preemptive SJF", 10, 360);
            g.setFont(currentFont);
            
            g.drawRect(50,365,370, 40);
            npSJF();
            int tsum=0;
            for(int i=0;i<mlist.size();i++){
                tsum+=mlist.get(i).bt;
            }
            for(int i=0;i<pn;i++){
                ct[i]=0;
                wat[i]=0;
                tat[i]=0;
            }
            int pos=0,c=0,textpos=0;
            for(int i=0;i<mlist.size();i++){
                pos+=370/tsum*mlist.get(i).bt;
                c+=mlist.get(i).bt;
                if(i!=mlist.size()-1)
                    g.drawLine(50+pos, 365, 50+pos, 405);
                g.drawString(c+"", 50+pos, 418);
                if(i==0){
                    g.drawString(mlist.get(i).proc, 53, 390);
                    textpos=pos;
                }
                else{
                    g.drawString(mlist.get(i).proc,53+textpos , 390);
                    textpos=pos;
                }
            }
            computeTime();
            int tatsum=0;
            int wsum=0;
            String tt="Turn Around Time: ";
            int s=0;
            for(int i=0;i<pn;i++){
                tatsum+=tat[i];
                String pname="P"+(i+1);
                tt+=(pname+"=");
                tt+=tat[i];
                s+=tat[i];
                if(i!=pn-1)
                    tt+=", ";
                else
                    tt+=" ";
            }
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            tt+="      Average TAT="+df.format((double)tatsum/pn);
            g.drawString(tt, 5, 430);
            tt="Waiting time: ";
            for (int i = 0; i < pn; i++) {
                wsum+=wat[i];
                tt+=procname[i];
                tt+="=";
                tt+=(wat[i]+"");
                if(i!=pn-1)
                    tt+=", ";
                
            }
            tt+="      Average WT="+df.format((double)wsum/pn);
            g.drawString(tt, 5, 443);
        }
        if(flag5==1){
            extractPN();
            extractAT();
            extractBT();
            extractProcname();
            extractPriority();
            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
            Font gt=newFont.deriveFont(Font.BOLD);
            g.setFont(gt);
            g.drawString("Preemptive Priority", 10, 475);
            g.setFont(currentFont);
            
            g.drawRect(50, 480, 370, 40);
            pPriority();
            int tsum=0;
            for (int i = 0; i < psjf.size(); i++) {
                tsum+=psjf.get(i).time;
            }
            for(int i=0;i<pn;i++){
                ct[i]=0;
                wat[i]=0;
                tat[i]=0;
            }
            int pos=0,c=0,textpos=0;
            for(int i=0;i<psjf.size();i++){
                pos+=370/tsum*psjf.get(i).time;
                c+=psjf.get(i).time;
                if(i!=psjf.size()-1)
                    g.drawLine(50+pos, 480, 50+pos, 520);
                g.drawString(c+"", 50+pos, 533);
                if(i==0){
                    g.drawString(psjf.get(i).proc, 53, 505);
                    textpos=pos;
                }
                else{
                    g.drawString(psjf.get(i).proc,53+textpos , 505);
                    textpos=pos;
                }
            }
            for (int i = 0; i < psjf.size(); i++) {
                int csum=0;
                int x=Integer.parseInt(psjf.get(i).proc.charAt(1)+"")-1;
                for (int j = 0; j <=i; j++) {
                    csum+=psjf.get(j).time;
                }
                ct[x]=csum;
            }
            int wsum=0;
            int tatsum=0;
            for (int i = 0; i < pn; i++) {
                tat[i]=ct[i]-Integer.parseInt(at[i]);
                tatsum+=tat[i];
                wat[i]=tat[i]-Integer.parseInt(bt[i]);  
                wsum+=wat[i];
            }
            String tt="Turnaround time: ";
            for (int i = 0; i < pn; i++) {
                tt+=procname[i];
                tt+="=";
                tt+=(tat[i]+"");
                if(i!=pn-1)
                    tt+=", ";
                else
                    tt+=" ";
            }
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            tt+="      Average TAT="+df.format((double)tatsum/pn);
            g.drawString(tt, 5, 547);
            tt="Waiting time: ";
            for (int i = 0; i < pn; i++) {
                tt+=procname[i];
                tt+="=";
                tt+=(wat[i]+"");
                if(i!=pn-1)
                    tt+=", ";
                
            }
            tt+="      Average WT="+df.format((double)wsum/pn);
            g.drawString(tt, 5, 560);
        }
        if(flag6==1){
            extractPN();
            extractAT();
            extractBT();
            extractProcname();
            extractPriority();
            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
            Font gt=newFont.deriveFont(Font.BOLD);
            g.setFont(gt);
            g.drawString("Non preemptive Priority", 10, 590);
            g.setFont(currentFont);
            
            g.drawRect(50,595,370, 40);
            npPriority();
            int tsum=0;
            for(int i=0;i<mlist.size();i++){
                tsum+=mlist.get(i).bt;
            }
            for(int i=0;i<pn;i++){
                ct[i]=0;
                wat[i]=0;
                tat[i]=0;
            }
            int pos=0,c=0,textpos=0;
            for(int i=0;i<mlist.size();i++){
                pos+=370/tsum*mlist.get(i).bt;
                c+=mlist.get(i).bt;
                if(i!=mlist.size()-1)
                    g.drawLine(50+pos, 595, 50+pos, 635);
                g.drawString(c+"", 50+pos, 648);
                if(i==0){
                    g.drawString(mlist.get(i).proc, 53, 610);
                    textpos=pos;
                }
                else{
                    g.drawString(mlist.get(i).proc,53+textpos , 610);
                    textpos=pos;
                }
            }
            computeTime();
            int tatsum=0;
            int wsum=0;
            String tt="Turn Around Time: ";
            int s=0;
            for(int i=0;i<pn;i++){
                tatsum+=tat[i];
                String pname="P"+(i+1);
                tt+=(pname+"=");
                tt+=tat[i];
                s+=tat[i];
                if(i!=pn-1)
                    tt+=", ";
                else
                    tt+=" ";
            }
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            tt+="      Average TAT="+df.format((double)tatsum/pn);
            g.drawString(tt, 5, 660);
            tt="Waiting time: ";
            for (int i = 0; i < pn; i++) {
                wsum+=wat[i];
                tt+=procname[i];
                tt+="=";
                tt+=(wat[i]+"");
                if(i!=pn-1)
                    tt+=", ";
                
            }
            tt+="      Average WT="+df.format((double)wsum/pn);
            g.drawString(tt, 5, 675);
        }
    }
    void computeTime(){
        int c=0;
        for(int i=0;i<pn;i++){
            c+=mlist.get(i).bt;
            int x=Integer.parseInt(mlist.get(i).proc.charAt(1)+"");
            ct[x-1]=c;
        }
        Collections.sort(mlist, new Comparator<sjfdatatype>() {
            @Override
            public int compare(sjfdatatype example1, sjfdatatype example2) {
                return example1.getVar2().compareTo(example2.getVar2());
            }
        });
        for(int i=0;i<pn;i++){
            tat[i]=ct[i]-mlist.get(i).at;
            wat[i]=tat[i]-mlist.get(i).bt;
        }
    }
    void FCFS(){
        
        for(int i=0;i<pn;i++){
            tempat[i]=at[i];
            tempbt[i]=bt[i];
        }
        String temp1,temp2,temp3;
        for(int i=0;i<pn-1;i++){
            for(int j=0;j<pn-1-i;j++){
                if(tempat[j].compareTo(tempat[j+1])>0){
                    temp1=tempat[j];
                    tempat[j]=tempat[j+1];
                    tempat[j+1]=temp1;
                    temp2=tempbt[j];
                    tempbt[j]=tempbt[j+1];
                    tempbt[j+1]=temp2;
                    temp3=procname[j];
                    procname[j]=procname[j+1];
                    procname[j+1]=temp3;
                }
            }
        }
        for(int i=0;i<pn;i++)
            System.out.print(tempat[i]+" "+tempbt[i]+"\n");
        int ctv=0;
        for(int i=0;i<pn;i++){
            ctv+=Integer.parseInt(tempbt[i]);
            ct[i]=ctv;
            tat[i]=ct[i]-Integer.parseInt(tempat[i]);
            wat[i]=tat[i]-Integer.parseInt(tempbt[i]);
        }
        int g = 0;
        for(int i=0;i<pn;i++){
            for(int j=0;j<pn;j++){
                if(at[i]==tempat[j])
                    g=j;
            }
        }
    }
    void roundRobin(){
        ArrayList<String> newArray = new ArrayList<String>();
        for(String s : procname){
            if(s!=null){
                newArray.add(s);
            }
        }
        Collections.sort(newArray);
        procname = newArray.toArray(procname);
        for(int i=0;i<pn;i++)
            tempat[i]="";
        for(int i=0;i<pn;i++)
            tempbt[i]="";
        for(int i=0;i<pn;i++){
            tempat[i]=at[i];
            tempbt[i]=bt[i];
        }
        String temp1,temp2,temp3;
        for(int i=0;i<pn-1;i++){
            for(int j=0;j<pn-1-i;j++){
                if(tempat[j].compareTo(tempat[j+1])>0){
                    temp1=tempat[j];
                    tempat[j]=tempat[j+1];
                    tempat[j+1]=temp1;
                    temp2=tempbt[j];
                    tempbt[j]=tempbt[j+1];
                    tempbt[j+1]=temp2;
                    temp3=procname[j];
                    procname[j]=procname[j+1];
                    procname[j+1]=temp3;
                }
            }
        }
        int[] flag=new int[pn];
        flag[0]=0;       
        int intf=1;
        do{
            intf=1;
            for(int i=0;i<pn;i++){
                if(flag[i]==0){
                    if(Integer.parseInt(tempbt[i])<=tq){
                        rr[rrn++]=new rrdatatype(Integer.parseInt(tempbt[i]),procname[i]);
                        flag[i]=1;
                        tempbt[i]="";
                    }
                    else{
                        rr[rrn++]=new rrdatatype(tq,procname[i]);
                        tempbt[i]=(Integer.parseInt(tempbt[i])-tq)+"";
                    }
                }
            }
            intf=1;
            for(int j=0;j<pn;j++){
                intf=intf*flag[j];
            }
        }while(intf==0);
    }
    void pSJF(){
        ArrayList<String> newArray = new ArrayList<String>();
        for(String s : procname){
            if(s!=null){
                newArray.add(s);
            }
        }
        Collections.sort(newArray);
        procname = newArray.toArray(procname);
        ArrayList<sjfdatatype> m=new ArrayList<sjfdatatype>();
        int t=0;
        for (int i = 0; i < pn; i++) {
            m.add(new sjfdatatype(Integer.parseInt(at[i]),Integer.parseInt(bt[i]),procname[i]));
            t+=Integer.parseInt(bt[i]);
        }
        
        ArrayList<String> l=new ArrayList<String>();
        
        int mpos=0;
        for (int i = 0; i < t; i++) {
            int min=1000;
            for (int j = 0; j < m.size(); j++) {
                if(m.get(j).at<=i){
                    if(m.get(j).bt>0){
                        if(m.get(j).bt<min){
                            min=m.get(j).bt;
                            mpos=j;
                        }
                    }
                }
            }
            l.add(m.get(mpos).proc);
            sjfdatatype temp=new sjfdatatype(m.get(mpos).at,m.get(mpos).bt-1,m.get(mpos).proc);
            m.set(mpos, temp);
        }
        
        int pt=0;
        String fs="";
        int ft=0;
        for (int i = 0; i < l.size(); i++) {
            if(i==0){
                fs=l.get(0);
                ft++;
            }
            else if(i==l.size()-1){
                if(l.get(i).equals(l.get(i-1))){
                    ft++;
                    psjf.add(new rrdatatype(ft,fs));
                }
                else{
                    psjf.add(new rrdatatype(ft,fs));
                    fs=l.get(i);
                    ft=1;
                    psjf.add(new rrdatatype(ft,fs));
                }
                    
            }
            else{
                if(l.get(i).equals(l.get(i-1))){
                    ft++;
                }
                else{
                    psjf.add(new rrdatatype(ft,fs));
                    fs=l.get(i);
                    ft=1;
                }
            }
        }
        for (int i = 0; i < psjf.size(); i++) {
            System.out.println(psjf.get(i).proc+" "+psjf.get(i).time);
        }
    }
    void npSJF(){
        ArrayList<String> newArray = new ArrayList<String>();
        for(String s : procname){
            if(s!=null){
                newArray.add(s);
            }
        }
        Collections.sort(newArray);
        procname = newArray.toArray(procname);
        ArrayList<sjfdatatype> glist=new ArrayList<sjfdatatype>();
        for(int i=0;i<pn;i++){
            sjfdatatype x=new sjfdatatype(Integer.parseInt(at[i]),Integer.parseInt(bt[i]),procname[i],0);
            glist.add(x);
        }
        int t=0;
        int smallpos=0;
        int fmul=1;
        do{
            fmul=1;
            int small=1000;
            for (int i = 0; i < pn; i++) {
                if(glist.get(i).sflag==0){
                    if(glist.get(i).at<=t){
                        if(glist.get(i).bt<small){
                            smallpos=i;
                            small=glist.get(i).bt;
                        }
                    }
                }
            }
            System.out.println(glist.get(smallpos).proc);
            mlist.add(new sjfdatatype(glist.get(smallpos).at,glist.get(smallpos).bt,glist.get(smallpos).proc,1));
            sjfdatatype temp=new sjfdatatype(glist.get(smallpos).at,glist.get(smallpos).bt,glist.get(smallpos).proc,1);
            
            glist.set(smallpos, temp);
            t+=glist.get(smallpos).bt;
            for (int i = 0; i < glist.size(); i++) {
                fmul*=glist.get(i).sflag;
            }
        }while(fmul==0);
        for (int i = 0; i < mlist.size(); i++) {
            System.out.println(mlist.get(i).proc);
        }
    }
    void npPriority(){
        ArrayList<String> newArray = new ArrayList<String>();
        for(String s : procname){
            if(s!=null){
                newArray.add(s);
            }
        }
        Collections.sort(newArray);
        procname = newArray.toArray(procname);
        ArrayList<sjfdatatype> glist=new ArrayList<sjfdatatype>();
        for(int i=0;i<pn;i++){
            sjfdatatype x=new sjfdatatype(Integer.parseInt(at[i]),Integer.parseInt(bt[i]),procname[i],priority[i],0);
            glist.add(x);
        }
        int t=0;
        mlist.removeAll(mlist);
        int smallpos=0;
        int fmul=1;
        do{
            fmul=1;
            int small=1000;
            for (int i = 0; i < pn; i++) {
                if(glist.get(i).sflag==0){
                    if(glist.get(i).at<=t){
                        if(Integer.parseInt(glist.get(i).pr)<small){
                            smallpos=i;
                            small=Integer.parseInt(glist.get(i).pr);
                        }
                    }
                }
            }
            mlist.add(new sjfdatatype(glist.get(smallpos).at,glist.get(smallpos).bt,glist.get(smallpos).proc));
            sjfdatatype temp=new sjfdatatype(glist.get(smallpos).at,glist.get(smallpos).bt,glist.get(smallpos).proc,glist.get(smallpos).pr,1);
            
            glist.set(smallpos, temp);
            t+=glist.get(smallpos).bt;
            for (int i = 0; i < glist.size(); i++) {
                fmul*=glist.get(i).sflag;
            }
        }while(fmul==0);
        for (int i = 0; i < mlist.size(); i++) {
            System.out.println(mlist.get(i).proc);
        }
    }
    void pPriority(){
        ArrayList<String> newArray = new ArrayList<String>();
        for(String s : procname){
            if(s!=null){
                newArray.add(s);
            }
        }
        Collections.sort(newArray);
        procname = newArray.toArray(procname);
        psjf.removeAll(psjf);
        ArrayList<sjfdatatype> m=new ArrayList<sjfdatatype>();
        int t=0;
        for (int i = 0; i < pn; i++) {
            m.add(new sjfdatatype(Integer.parseInt(at[i]),Integer.parseInt(bt[i]),procname[i],priority[i]));
            t+=Integer.parseInt(bt[i]);
        }
        
        ArrayList<String> l=new ArrayList<String>();
        
        int mpos=0;
        for (int i = 0; i < t; i++) {
            int min=100;
            for (int j = 0; j < m.size(); j++) {
                if(m.get(j).at<=i){
                    if(m.get(j).bt>0){
                        if(Integer.parseInt(m.get(j).pr)<min){
                            min=Integer.parseInt(m.get(j).pr);
                            mpos=j;
                        }
                    }
                }
            }
            l.add(m.get(mpos).proc);
            sjfdatatype temp=new sjfdatatype(m.get(mpos).at,m.get(mpos).bt-1,m.get(mpos).proc,m.get(mpos).pr);
            m.set(mpos, temp);
        }
        
        int pt=0;
        String fs="";
        int ft=0;
        for (int i = 0; i < l.size(); i++) {
            if(i==0){
                fs=l.get(0);
                ft++;
            }
            else if(i==l.size()-1){
                if(l.get(i).equals(l.get(i-1))){
                    ft++;
                    psjf.add(new rrdatatype(ft,fs));
                }
                else{
                    psjf.add(new rrdatatype(ft,fs));
                    fs=l.get(i);
                    ft=1;
                    psjf.add(new rrdatatype(ft,fs));
                }
                    
            }
            else{
                if(l.get(i).equals(l.get(i-1))){
                    ft++;
                }
                else{
                    psjf.add(new rrdatatype(ft,fs));
                    fs=l.get(i);
                    ft=1;
                }
            }
        }
        for (int i = 0; i < psjf.size(); i++) {
            System.out.println(psjf.get(i).proc+" "+psjf.get(i).time);
        }
    }
    public static void main(String args[]){
        
        cpuscheduling panel=new cpuscheduling();
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setSize(500, 720);
        f2.add(panel);
        f2.setVisible(false);
    }
}
class rrdatatype{
    int time;
    String proc;
    rrdatatype(int t,String p){
        this.time=t;
        this.proc=p;
    }
}
class sjfdatatype{
    int at;
    int bt;
    String proc;
    String pr;
    int sflag;
    sjfdatatype(int at, int bt,String p){
        this.at=at;
        this.bt=bt;
        this.proc=p;
    }
    sjfdatatype(int at, int bt,String p,int f){
        this.at=at;
        this.bt=bt;
        this.proc=p;
        this.sflag=f;
    }
    sjfdatatype(int at, int bt,String p,String pr){
        this.at=at;
        this.bt=bt;
        this.proc=p;
        this.pr=pr;
    }
    sjfdatatype(int at, int bt,String p,String pr,int f){
        this.at=at;
        this.bt=bt;
        this.proc=p;
        this.pr=pr;
        this.sflag=f;
    }
    public String getVar1(){
        String b=bt+"";
        return b;
    }
    public String getVar2(){
        return proc;
    }
    public String getVar3(){
        return pr;
    }
}