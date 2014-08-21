import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class NetworkAddr{
	String name;
	InetAddress ip;
	String mac;
	
	void setName(String _name){
		name = _name;
	}
	String getName(){
		return name;
	}
	
	void setIp(InetAddress _ip){
		ip = _ip;
	}
	InetAddress getIp(){
		return ip;
	} 
	
	void setMac(String _mac){
		mac = _mac;
	}
	String getMac(){
		return mac;
	}
	
}

enum EnumPrintAddrType{
	PRINT_NAME,
	PRINT_IP,
	PRINT_MAC,
	PRINT_ALL;
};

public class SysInfo{
    
    private ArrayList<NetworkAddr> mAddrs  = null;
    
    public SysInfo(){
    	mAddrs = new ArrayList<NetworkAddr>(10);
    }

    
    // 
    public void getIps(){
    	getIps(false);
    }
    // 获取本机所有的ip地址,如果参数为false，那么使用上一次使用的结果
    public void getIps(boolean iForce){
        
        String name   = null;
        if(!iForce && mAddrs.size()>0 ){
         	return;  // 使用上次的getIps的结果
        }

        // 清除上次的结果
        if(mAddrs.size() >0){
        	mAddrs.clear();
        }

        try{
        	// 枚举出当前系统中的所有网络地址
            for(Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();e.hasMoreElements();){
                NetworkInterface item = e.nextElement();
                name = item.toString();

                for (InterfaceAddress address : item.getInterfaceAddresses()) {
                	NetworkAddr addr = new NetworkAddr();
                    if(address.getAddress() instanceof Inet4Address){
                        Inet4Address net4Addr = (Inet4Address)address.getAddress();
                        if( name!=null && name.length()>0){
                            //ipAddrs.put(key,value);
                            //netAddrs.add(net4Addr);
                            addr.setName(name);
                            addr.setIp(net4Addr);
                            
                            // get mac addrs
                    		NetworkInterface network = NetworkInterface.getByInetAddress(net4Addr); 
                    		if(null != network){
                    			byte[] mac = network.getHardwareAddress();
                    			if(null != mac){
                    				StringBuilder sb = new StringBuilder();
                    				for(int i=0;i<mac.length;i++){
                    					sb.append(String.format("%02x%s",mac[i],(i<mac.length-1)?"-":""));
                    				}
                    				//System.out.println(sb.toString());
                    				addr.setMac(sb.toString());
                    			}
                    		}  // end null != network
                            if(null != addr.getName() && addr.getName().length()>0){
                            	mAddrs.add(addr);
                            }
                        }// end key!=null && key.length()>0

                    }  
                }  
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return;
    }

    // 打印本机所有的ip地址
    public void printIps(){
    	printNetworkAddrs(EnumPrintAddrType.PRINT_IP);
    }
    
    public void printFullAddrs(){
    	printNetworkAddrs(EnumPrintAddrType.PRINT_ALL);
    }
    
    public void printNetworkAddrs(EnumPrintAddrType type){
    	if(type == null){
    		return;
    	}
    	
    	try{
    	for(NetworkAddr addr:mAddrs){
    		switch(type){
    		case PRINT_NAME:
    			System.out.println(addr.getName());
    			break;
    		case PRINT_IP:
    			System.out.println(addr.getIp().getHostAddress());
    			break;
    		case PRINT_MAC:
    			System.out.println(addr.getMac());
    			break;
    		case PRINT_ALL:
    			System.out.println(addr.getName() + ":" + addr.getIp().getHostAddress() + ":" + addr.getMac());
    			break;
    		default :
    			throw new Exception("printNetWorkAddrs"); 
    		}
    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public static void main(String[] args){
    	SysInfo sysInfo = new SysInfo();
        //sysInfo.printFullAddrs();
    	sysInfo.getIps();
    	sysInfo.printFullAddrs();
    }
}