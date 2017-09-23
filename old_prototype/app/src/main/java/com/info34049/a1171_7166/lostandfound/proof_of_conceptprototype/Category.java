package com.info34049.a1171_7166.lostandfound.proof_of_conceptprototype;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Adam on 2017-04-11.
 */

/**
 * Represents a category of lost or found items. Categories can have child categories;
 * for example, you might have "Android phone" be a child of "smartphone," or "sandwich" be a child
 * of "food." Also, every category that is not a root category has a parent. A root category
 * basically means "the everything category."
 */
public class Category implements Serializable {
	private String name;
	private Category parent;
	private ArrayList<Category> children;
	
	private Category(String name, Category parent, ArrayList<Category> children){
		this.name = name;
		this.parent = parent;
		this.children = children;
	};
	
	/**
	 * Creates a new root category, to hold all top-level categories as its children
	 * @param name the name of the root category
	 * @return the newly created category
	 */
	private static Category createRoot(String name) {
		return new Category(name, null, new ArrayList<Category>());
	}
	
	/**
	 * Creates a new category as a child of this one
	 * @param childName the name of the new child category
	 * @return the new child category
	 */
	private Category newChild(String childName) {
		Category child = new Category(childName, this, new ArrayList<Category>());
		children.add(child);
		return child;
	}
	
	/**
	 * Removes a child category from this one. If there is no such child, nothing happens
	 * @param childName the name of the child to be removed
	 */
	private void removeChild(String childName) {
		for (int i = 0; i<children.size(); i++) {
			if (children.get(i).name.equals(childName)) {
				children.remove(i);
				break;
			}
		}
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * @return the name of this category's parent category
	 */
	public Category getParent() {
		return parent;
	}
	
	/**
	 * finds one of this category's children by name
	 * @param name the name of the child category
	 * @return the child category, or null if there is no child by that name
	 */
	public Category getChild(String name) {
		for (Category c : children) {
			if (c.name.equals(name)) return c;
		}
		return null;
	}
	
	/**
	 * @return an array of all of this category's children
	 */
	public Category[] getChildren() {
		return children.toArray(new Category[children.size()]);
	}
	
	
	
	public static Category getSingletonRoot() {
		return singRoot;
	}
	private static Category singRoot = null;
	private static boolean isInit = false;
	public static void singletonInit() {
		if (isInit) return;
		isInit = true;
		singRoot = Category.createRoot("root");
		Category elec = singRoot.newChild("Electronics");
		singRoot.newChild("Jewelery");
		elec.newChild("Smartphones").newChild("Android Phones");
		elec.getChild("Smartphones").newChild("iPhones");
		
		//create a bunch more categories and sub-categories (children) here
	}
}
