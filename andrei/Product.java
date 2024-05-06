package andrei;

public class Product {
    private int id;
    private String productType;
    private String productName;
    private float price;
    private int quantity;
    private String img; // Changed from "img" to "imageURL"

    public Product(int id, String productType, String productName, float price, int quantity, String img) {
		super();
		this.id = id;
		this.productType = productType;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.img = img;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String productType, String productName, double d, int quantity, String img) {
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageURL() {
        return this.getImageURL();
    }

    public void setImageURL(String imageURL) {
        this.img = imageURL;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Product [id=" + id + ", productType=" + productType + ", productName=" + productName + ", price="
                + price + ", quantity=" + quantity + ", imageURL=" + img + "]"; // Changed from "img" to "imageURL"
    }
}