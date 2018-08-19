class node{
	int name;
	int neighbours;
	boolean visit;
	double huristic;
	node parent;
	//int adj[];
	int adj[];
	double g;
	node(){
	
	}
	public node(int name,double huristic,int neighbours,int adj[]){
		this.name=name;
		this.visit=false;
		this.parent=null;
		this.g=0;
		this.huristic=huristic;
		this.adj=adj;
		this.neighbours=neighbours;
	}
	public void show(){
		if(parent==null){
		System.out.print("At  ["+name+"] ROOT G----> "+g+" ADJ-->  ");
		}
		else{
		System.out.print("At  ["+name+"] Parent ["+parent.getname()+"] G----> "+g+" ADJ-->  ");
		}
		
		
		for(int i=0;i<neighbours;i++){
			System.out.print(adj[i]+" ");
		}
		System.out.println("");
	}
	public void setparent(node parent){this.parent=parent;};
        public void setvisit(boolean visit){this.visit=visit;};
	public node getparent(){return parent;}
	public boolean getvisit(){return visit;}
	public int getname(){return name;}
	public double gethuristic(){return huristic;}
	public int[] getadj(){return adj;};
        public void sethuristic(double huristic){this.huristic= huristic;}
        public double get_g(){return g;}
        public void set_g(double g){this.g=g;}
}