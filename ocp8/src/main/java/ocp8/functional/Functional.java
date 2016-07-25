package ocp8.functional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Functional {

	public static void main(String[] args) {
		
		/* reduce method */ 
//		test1();
		
		/* Intermediate Ops */
//		test2();
		
		/* Collectors.mapping() */
//		test3();
		
		/* Parallel reduction */
		test4();
		
	}
	
	static void test1() {
		Stream<String> empty = Stream.empty();
		String reduce1 = empty.reduce("", (a,b) -> a + b);
		System.out.println("reduce1: " + reduce1);
		
		Stream<Integer> stream = Stream.of(3,5,6);
		Integer reduce2 = stream.reduce(1, (a,b)->a*b,(a,b)->a*b);
		System.out.println("reduce2: " + reduce2);
	}
	
	static void test2() {
        Stream<String> stringStream = Stream.of("lion", "tiger", "bear");
		
		/* Intermediate Ops */
		stringStream.map(String::length).forEach(s -> {System.out.print(s + ", ");});
		stringStream.map(s -> s.length()).forEach(s -> {System.out.print(s + ", ");});
	}
	
	static void test3() {
		Stream<String> stringStream = Stream.of("lion", "tiger", "bear");
		
		Optional<Integer> mappingTest1 = stringStream.collect(Collectors.mapping(s->s.length(), Collectors.minBy(Comparator.naturalOrder())));System.out.println("mappingTest1: " + mappingTest1);
		Map<Integer, Optional<Character>> mappingTest2 = stringStream.collect(
				                                                         Collectors.groupingBy(
				                                                        		      String::length, 
				                                                        		      Collectors.mapping(s->s.charAt(0), 
				                                                        		    		        Collectors.minBy(Comparator.naturalOrder()))));System.out.println("mappingTest2: " + mappingTest2);
	}
	
	static void test4() {
		System.out.println(Arrays.asList("duck","chicken","flamingo","pelican")
				.parallelStream()
				.parallel() //this is valid parallelStream().parallel()
				.reduce(0, (c1, c2) -> c1 + c2.length(), (s1, s2) -> s1 + s2)); 
		        //how come c1 become an Int ??? becoz reduce() has signature reduce(T identity, BiFunction<T,? super U, T>, BinOpt<T>)
	}
	
	static void test5() {
		ExecutorService service = Executors.newScheduledThreadPool(10);
		DoubleStream.of(3.14159,2.71828) // b1
		.forEach(c -> service.submit( // b2
		() -> System.out.println(10*c))); // b3
		service.execute(() -> System.out.println("Printed")); // b4
	}

}
