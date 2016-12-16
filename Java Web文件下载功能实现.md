#  Java Web文件下载功能实现 #
实现一个具有文件下载功能的的网页  


----------
  
两种实现方法：  
## 一：通过超链接实现下载  ## 
在HTML网页中，通过超链接链接到要下载的文件的地址

 	`<a href="./ajaxfileupload_JS_File.rar">下载1</a>`
 	`<a href="./original.jpg">下载图片</a>`


![](http://i.imgur.com/c2gM25O.png)  

程序运行后，可以通过单击需要下载文档实现下载  
![](http://i.imgur.com/ROYqSrQ.png)  
但是这里会出现一个问题，就是单击下载压缩包的时候会弹出下载页面，但是下载图片的时候浏览器就直接打开了图片，没有下载。  
![](http://i.imgur.com/X5rPifs.png)
<font size=4 color="blue" face="黑体">这是因为通过超链接下载文件时，如果浏览器可以识别该文件格式，浏览器就会直接打开。只有浏览器不能识别该文件格式的时候 ，</font>才会实现下载。因此利用第二种方法实现下载功能   
##  二：通过Servlet程序实现下载 ##
通过Servlet下载文件的原理是通过servlet读取目标程序，将资源返回客户端。 
> `<a href="./ajaxfileupload_JS_File.rar">下载1</a>`  

> `<a href="./original.jpg">下载图片</a>`  

    @RequestMapping(value = "download")
    public void dataDownload(HttpServletRequest request, HttpServletResponse response){
    //获得请求文件名  
        String filename = request.getParameter("filename");  
        System.out.println(filename);  
		//获取文件路径
        File file = new File("./filename");
        //设置文件MIME类型  
        response.setContentType(request.getSession()getServletContext.getMimeType(filename));  
        //设置Content-Disposition  
        response.setHeader("Content-Disposition", "attachment;filename="+filename);  
       
    InputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream out = null;
    BufferedOutputStream bos = null;
    try{
        fis = new FileInputStream(file);//读取要下载的文件
        bis = new BufferedInputStream(fis);
        out = response.getOutputStream();//创建输出流
        bos = new BufferedOutputStream(out);
        byte[] b = new byte[1024];//创建缓冲区
        int len;
        while((len = fis.read(b)) != -1){//将输入流中的内容读取到缓冲区
            out.write(b,0,len);
        }
        bos.close();
        out.close();
        bis.close();
        fis.close();
    }catch (Exception e){
        e.printStackTrace();
    }
    }  
##  三：java中获取ServletContext常见方法  ##
 1.在javax.servlet.Filter中直接获取  

	ServletContext context = config.getServletContext();

2.在HttpServlet中直接获取  

	this.getServletContext()

3.在其他方法中，通过HttpRequest获得  

	request.getSession().getServletContext();
