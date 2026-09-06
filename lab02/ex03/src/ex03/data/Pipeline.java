package ex03.data;
import java.util.ArrayList;
import java.util.List;


public class Pipeline<T,R> {
	//to do as practice before quiz
}

/*public interface Transformer<T,R> {
	R transform(T input);
	
	default <N> Transformer<T,N> andThen(Transformer<R,N> after) {
		return (T input) -> after.transform(this.transform(input));
}
*/