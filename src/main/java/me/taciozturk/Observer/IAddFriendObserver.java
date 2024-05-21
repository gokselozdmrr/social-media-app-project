package me.taciozturk.Observer;

public interface IAddFriendObserver {

    //Arkadaş eklendiği zaman midpaneldeki postlarda ve rightpaneldeki arkadaş bilgisi
    //güncellemesi için observer pattern kullanıyoruz
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObserver(String type);

}
