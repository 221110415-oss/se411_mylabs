package ex03.data;

public class Pipeline<T,R> {
	
	//everything added so far, chained into a single transformation from T to R
	private final Transformer<T,R> transformers;
	
	private Pipeline(Transformer<T,R> transformers) {
		this.transformers = transformers;
	}
	
	//start of a pipeline, nothing is transformed yet (T -> T)
	public static <T> Pipeline<T,T> start() {
		return new Pipeline<T,T>(input -> input);
	}
	
	//adds a transformer that may change the type, returns a new pipeline with the updated output type
	public <N> Pipeline<T,N> add(Transformer<R,N> next) {
		return new Pipeline<T,N>(transformers.andThen(next));
	}
	
	//applies all the transformations in order
	public R execute(T input) {
		return transformers.transform(input);
	}
}
