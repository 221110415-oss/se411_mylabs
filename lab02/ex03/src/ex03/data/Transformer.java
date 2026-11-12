package ex03.data;

public interface Transformer<T,R> {
	R transform(T input);
	
	default <N> Transformer<T,N> andThen(Transformer<R,N> after) {
		return (T input) -> after.transform(this.transform(input));
	}
}
