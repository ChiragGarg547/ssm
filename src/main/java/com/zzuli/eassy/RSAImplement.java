package com.zzuli.eassy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-1-10 10:25
 * Created with IDEA
 */
public class RSAImplement {
    //coming from Euler Theorem
    //static variable
    private static BigInteger p = new BigInteger("4");

    private static BigInteger q = new BigInteger("4");

    private final static BigInteger e = new BigInteger("65537");

    private static BigInteger d;

    private static BigInteger n;

    // no keys and generate new ones
    public RSAImplement(){
        while(!p.isProbablePrime(3)) {
            p = BigInteger.probablePrime(1024, new Random());
        }

        while(!q.isProbablePrime(3)) {
            q = BigInteger.probablePrime(1024, new Random());
        }
        this.generateVariable();
    }

    //already has keys and init the keys
    public RSAImplement(BigInteger d1, BigInteger n1){
        d = d1;
        n = n1;
    }

    //essential variable (e,d,n)
    //public (n,e)
    //private (n,d)
    //server own      n
    //user own      d
    private void generateVariable(){
        n = p.multiply(q);
        BigInteger rulerVar = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        //e*x + rulerVar*y = 1
        d = ex_gcd(e,rulerVar).get(1);
        System.out.println("p: " + p);
        System.out.println("q: " + q);
        System.out.println("e: " + e);
        System.out.println("d: " + d);
        System.out.println("n: " + n);
    }
    private List<BigInteger> ex_gcd(BigInteger a, BigInteger b){
        List<BigInteger> backList = new ArrayList<>();
        if(b.intValue() == 0 ){
            backList.add(a);
            backList.add(new BigInteger("1"));
            backList.add(new BigInteger("0"));
            return backList;
        }
        List<BigInteger> tempList = ex_gcd(b,a.mod(b));
        backList.add(tempList.get(0));
        backList.add(tempList.get(2));
        backList.add(tempList.get(1).subtract(a.divide(b).multiply(tempList.get(2))));
        return backList;
    }

    private boolean judgeCoprime(int a, int b){
        if(a < 0 || b < 0) return false;
        if(a < b){
            int temp = a;
            a = b;
            b = temp;
        }
        int temp = 0;
        while(true){
            temp = a % b;
            if(temp == 0){
                break;
            }else{
                a = b;
                b = temp;
            }
        }
        if(b == 1) return true;
        return false;
    }


    public BigInteger encrypt(BigInteger info){
        return info.modPow(e,n);
    }

    public BigInteger encode(BigInteger code){
        return code.modPow(d,n);
    }

    public BigInteger getD() {
        return d;
    }

    public BigInteger getN() {
        return n;
    }

    public static void main(String[] args) {
//        try{
//            SHAImplement sha = new SHAImplement();
//            RSAImplement rsaImplement  = new RSAImplement();
//            String data = "";
//            //            String data = FileTool.readToString("C:\\Users\\Administrator\\Desktop\\学习十九大-张成远.docx");
//            System.out.println("原始数据:"+ data);
//            String digest =sha.process(data);
//            System.out.println("摘要："+ digest);
//            //生成私钥密钥
//            rsaImplement.generateVariable();
//            byte[] infoBytes = digest.getBytes("UTF-8");
//            BigInteger infoBigInteger = new BigInteger(infoBytes);
//            BigInteger code = rsaImplement.encrypt(infoBigInteger);
//            System.out.println("密文："+code);
//            BigInteger decodeBigInteger = rsaImplement.encode(code);
//            byte[] encodeArray = decodeBigInteger.toByteArray();
//            System.out.println("解析："+ new String(encodeArray,"UTF-8"));
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }
}
