package ddd.magdy.fashione_commerace.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.model.ReviewModel;

public class NotificationViewModel extends ViewModel {



    public List<ReviewModel> reviewModelList(){
        List<ReviewModel> reviews = new ArrayList<>();
        reviews.add(new ReviewModel(R.drawable.image_review1,"Amira Al aidy","Arrived in perfect condition. I couldn\\'t be any happier in receiving it all the way here in PH","2 hours ago"));
        reviews.add(new ReviewModel(R.drawable.image_review2,"Mohamed Kamel","This book turned out to be much more than I expected. It has style and color advice plus history of how current western business dress evolved","3 hours ago"));
        reviews.add(new ReviewModel(R.drawable.image_review3,"Ahmed Sameer","This book turned out to be much more than I expected. It has style and color advice plus history of how current western business dress evolved","6 hours ago"));
        reviews.add(new ReviewModel(R.drawable.image_review4,"Sama Sayed","This is a great book about achieving a timeless look and acquiring quality apparel","1 hours ago"));
        reviews.add(new ReviewModel(R.drawable.image_review5,"Ali El-masry","Arrived in perfect condition. I couldn\\'t be any happier in receiving it","4 hours ago"));
        reviews.add(new ReviewModel(R.drawable.image_review1,"Rofida Moneer","Great historical advice for a different time. Still good advice for upper levels of business.","3 hours ago"));
        reviews.add(new ReviewModel(R.drawable.image_review2,"Ahmed Mohamed","very well written & essential useful information & reference quide\n","10 hours ago"));
        reviews.add(new ReviewModel(R.drawable.image_review3,"Sameh Ramadan","This is a great book about achieving a timeless look and acquiring quality apparel","2 hours ago"));
        reviews.add(new ReviewModel(R.drawable.image_review4,"Lamyaa Khaled","oh my good this is very nice ","3 hours ago"));
        reviews.add(new ReviewModel(R.drawable.image_review5,"Alaa Mohamed","This is my first \"fashion\" book. I used to think i understood a good fit","1 hours ago"));
        reviews.add(new ReviewModel(R.drawable.image_review1,"Zaynab Alaa","Great historical advice for a different time. Still good advice for upper levels of business.","5 hours ago"));
        reviews.add(new ReviewModel(R.drawable.image_review2,"Tamer Morsy","i happy to do this thinks for your interested "," 2 day ago"));
        reviews.add(new ReviewModel(R.drawable.image_review3,"Gerges Melad","very well written & essential useful information & reference quide\n","just now"));
        reviews.add(new ReviewModel(R.drawable.image_review4,"Lamees Morad","This is my first \"fashion\" book. I used to think i understood a good fit","4 minute ago"));
        reviews.add(new ReviewModel(R.drawable.image_review5,"Jamal Sherief","Great historical advice for a different time. Still good advice for upper levels of business.","3 week ago"));

        return reviews;
    }

}
