public interface Storage<T>{
    public boolean add(T object);
    public boolean delete(String uniqueIdentifier);
    public User get(String uniqueIdentifier);
}
