package examen;




public class stack<T> {

    
   private node<T> top;
   
   
   
   
    
    public void push(T value){
        node <T> newNode= new  node<T> (value);
        if (top==null){
            top=newNode;
        }else{
            newNode.setNext(top);
            top=newNode;
        } 
    }  
    public T pop(){
     node<T> popped_value = top;
     if (top!=null){
         top=top.getNext();
         return popped_value.getValue();  
     } else{
         return null;    
       }
    }
}
