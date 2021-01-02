 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tuhu.summary.facade.response.TireCommentTagResponse;

public class Test {

	public static void main(String[] args) { 
		
		List<String> list=new ArrayList<String>();
		list.add("111");
		
		list.add("222");

		System.out.println(list);
		Collections.reverse(list);
		
		System.out.println(list);
		System.out.println(1%3000);
	}

}
