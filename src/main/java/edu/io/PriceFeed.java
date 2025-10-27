package edu.io;

public class PriceFeed {
    public Publisher<DataPack> publisher = new Publisher<>();
    private DataSource dataSource;

    public PriceFeed() {
        this.dataSource = new HttpDataSource();
        new Thread(this::refreshData).start();
    }

    public PriceFeed(DataSource dataSource) {
        this.dataSource = dataSource;
        new Thread(this::refreshData).start();
    }

    public void refresh() {
        DataPack dp = dataSource.getData();
        publisher.publish(dp);
    }

    private void refreshData() {
        while(true) {
            try {
                Thread.sleep(3000);
                refresh();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
