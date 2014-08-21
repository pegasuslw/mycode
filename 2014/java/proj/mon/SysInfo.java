import java.net.InetAddress;
import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.*;

public class SysInfo{
    public SysInfo(){}

    public static HashMap<String,String> getIp(){
        
        HashMap<String,String> ipAddrs = new HashMap<String,String>(10);
        String key   = null;
        String value = null;

        try{
            //only get 127.0.0.1
            //InetAddress addr = InetAddress.getLocalHost();
            //ipAddr = addr.getHostAddress();

            for(Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();e.hasMoreElements();){
                NetworkInterface item = e.nextElement();
                key = item.toString();
                //System.out.println(item.toString());
                //System.out.println(item.getMTU() + " " + item.isLoopback() + " " + item.isPointToPoint() + " " + item.isUp() + " " + item.isVirtual());

                for (InterfaceAddress address : item.getInterfaceAddresses()) {
                    if(address.getAddress() instanceof Inet4Address){
                        Inet4Address net4Addr = (Inet4Address)address.getAddress();
                        value = net4Addr.getHostAddress().toString();
                        //System.out.println(net4Addr.getHostAddress());
                        if( (key!=null && key.length()>0)
                            && 
                            value!=null && value.length()>0
                          ){
                            ipAddrs.put(key,value);
                        }
                    }
                }  
            }

        }catch(Exception e){
            e.printStackTrace();
        }


        return ipAddrs;

    }

    public static void printMap(Map<String,String> map){
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key+":"+val);
        }
    }

    public static void main(String[] args){
        HashMap<String,String> ips = SysInfo.getIp();
        printMap(ips);
        //System.out.println();
    }


}