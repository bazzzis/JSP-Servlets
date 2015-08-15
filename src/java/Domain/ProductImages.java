/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author bazziss
 */
public class ProductImages {
    
    private int id;
    private String fileName;
    private String folderName;
    private byte[] imageData;
    private int productId;
    private boolean mainImage;
    

    public ProductImages() {
    }

    public ProductImages( String fileName, String folderName, byte[] imageData, int productId, boolean mainImage) {
       
        this.fileName = fileName;
        this.folderName = folderName;
        this.imageData = imageData;
        this.productId = productId;
        this.mainImage = mainImage;
    }

    public ProductImages(int id, String fileName, String folderName, int productId, boolean mainImage) {
        this.id = id;
        this.fileName = fileName;
        this.folderName = folderName;
        this.productId = productId;
        this.mainImage = mainImage;
    }
    

    public boolean isMainImage() {
        return mainImage;
    }

    public void setMainImage(boolean mainImage) {
        this.mainImage = mainImage;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductImages{" + "id=" + id + ", fileName=" + fileName + ", folderName=" + folderName + ", imageData=" + imageData + ", productId=" + productId + ", mainImage=" + mainImage + '}';
    }

    
}

