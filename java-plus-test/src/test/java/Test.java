 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
 

public class Test {

	public static void main(String[] args) { 
		
		List<String> list=new ArrayList<String>();
		list.add("111");
		
		list.add("222");

		System.out.println(list);
		Collections.reverse(list);
		
		System.out.println(list);
		System.out.println(1%3000);
		
		ConcurrentHashMap<String,String> map=new ConcurrentHashMap<>();
		map.put("", "");
	}

}
