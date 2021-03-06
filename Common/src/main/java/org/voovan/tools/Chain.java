package org.voovan.tools;

import java.util.ArrayDeque;
import java.util.Iterator;
/**
 * 对象链
 * 
 * @author helyho
 *
 * Voovan Framework.
 * WebSite: https://github.com/helyho/Voovan
 * Licence: Apache v2 License
 */
public class Chain<E>  extends ArrayDeque<E> implements Cloneable{
	private Iterator<E> iterator;
	private Iterator<E> invertedIterator;
	private boolean isStop;
	private E currentObj;
	
	/**
	 * 构造函数
	 */
	public Chain(){
		isStop = false;
		rewind();
	}
	
	/**
	 * 重置链的迭代器
	 */
	public void rewind(){
		isStop = false;
		iterator = this.iterator();
		invertedIterator = this.descendingIterator();
	}
	
	/**
	 * 迭代完成
	 */
	public void stop(){
		this.isStop = true;
	}

	/**
	 * 迭代器当前元素
	 * @return 当前元素
	 */
	public E current(){
		return currentObj;
	}

	/**
	 * 迭代器下一个元素
	 * @return 下一个元素
	 */
	public E next(){
		if(isStop){
			return null;
		} else {
			if(iterator.hasNext()){
				currentObj = iterator.next();
				return currentObj;
			} else {
				return null;
			}
		}
	}

	/**
	 * 迭代器是否有下一个对象
	 * @return 是否有下一个对象
	 */
	public boolean hasNext(){
		if(isStop){
			return false;
		} else {
			return iterator.hasNext();
		}
	}

	/**
	 * 迭代器上一个元素
	 * @return 上一个元素
	 */
	public E previous(){
		if(isStop){
			return null;
		} else {
			if(invertedIterator.hasNext()){
				currentObj = invertedIterator.next();
				return currentObj;
			} else {
				return null;
			}
		}
	}

	/**
	 * 迭代器是否有上一个对象
	 * @return 是否有上一个对象
	 */
	public boolean hasPrevious(){
		if(isStop){
			return false;
		} else {
			return invertedIterator.hasNext();
		}
	}

	/**
	 *  从当前对象克隆一个 Chain
	 *  @return 克隆后的对象
	 */
	@Override
	public Chain<E> clone(){
		ArrayDeque<E> cloned = super.clone();
		Chain<E> chain = new Chain<E>();
		chain.addAll(cloned);
		chain.rewind();
		cloned.clear();
		return chain;
	}
}
