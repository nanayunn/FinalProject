package com.example.appver2;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;


import com.example.appver2.CCFragment.CCAGENCYFragment;
import com.example.appver2.CCFragment.CCNameFragment;
import com.example.appver2.CCFragment.CCNumberFragment;
import com.example.appver2.CCFragment.CCSecureCodeFragment;
import com.example.appver2.CCFragment.CCValidityFragment;
import com.example.appver2.Utils.CreditCardUtils;
import com.example.appver2.Utils.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckOutActivity extends FragmentActivity implements FragmentManager.OnBackStackChangedListener {

    @BindView(R.id.btnNext)
    Button btnNext;

    public CardFrontFragment cardFrontFragment;
    public CardBackFragment cardBackFragment;

    //This is our viewPager
    private ViewPager viewPager;

    CCAGENCYFragment agencyFragment;
    CCNumberFragment numberFragment;
    CCNameFragment nameFragment;
    CCValidityFragment validityFragment;
    CCSecureCodeFragment secureCodeFragment;

    int total_item;
    boolean backTrack = false;

    private boolean mShowingBack = false;

    String cardNumber, cardCVV, cardValidity, cardName, cardAgency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        ButterKnife.bind(this);


        cardFrontFragment = new CardFrontFragment();
        cardBackFragment = new CardBackFragment();

        if (savedInstanceState == null) {
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, cardFrontFragment).commit();

        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }

        getFragmentManager().addOnBackStackChangedListener(this);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(4);
        setupViewPager(viewPager);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == total_item)
                    btnNext.setText("SUBMIT");
                else
                    btnNext.setText("NEXT");

                Log.d("track", "onPageSelected: " + position);

                if (position == total_item) {
                    flipCard();
                    backTrack = true;
                } else if (position == total_item - 1 && backTrack) {
                    flipCard();
                    backTrack = false;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = viewPager.getCurrentItem();
                if (pos < total_item) {
                    viewPager.setCurrentItem(pos + 1);
                } else {
                    checkEntries();
                }

            }
        });


    }

    public void checkEntries() {
        cardName = nameFragment.getName();
        cardAgency = agencyFragment.getAgency();
        cardNumber = numberFragment.getCardNumber();
        cardValidity = validityFragment.getValidity();
        cardCVV = secureCodeFragment.getValue();

        if (TextUtils.isEmpty(cardName)) {
            Toast.makeText(CheckOutActivity.this, "Enter Valid Name", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(cardAgency)  ) {
            Toast.makeText(CheckOutActivity.this, "Enter Valid card agency", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(cardNumber) || !CreditCardUtils.isValid(cardNumber.replace(" ",""))) {
            Toast.makeText(CheckOutActivity.this, "Enter Valid card number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cardValidity)||!CreditCardUtils.isValidDate(cardValidity)) {
            Toast.makeText(CheckOutActivity.this, "Enter correct validity", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cardCVV)||cardCVV.length()<3) {
            Toast.makeText(CheckOutActivity.this, "Enter valid security number", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(CheckOutActivity.this, "Your card is added", Toast.LENGTH_SHORT).show();
            Toast.makeText(CheckOutActivity.this, "card Agency:"+cardAgency+"cardName"+cardName+"'s Info:"+"\n"+"Card Number:"+cardNumber+"\n"+"card cvv:"+cardCVV+"\n"+
                    "card validity:"+cardValidity,Toast.LENGTH_LONG).show();


            //2초후 main 이동, 값 받아오기
            Handler hand = new Handler();
             hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(CheckOutActivity.this, MainActivity.class);

                i.putExtra("cardname",cardName);
                i.putExtra("cardAgency",cardAgency);
                i.putExtra("cardNumber",cardNumber);
                i.putExtra("cardCVV",cardCVV);
                i.putExtra("cardValidity",cardValidity);
                startActivity(i);
                finish();
            }
        },2000);




            Log.d("--------------","cardagency:"+cardAgency+"cardname:"+cardName+"cardnumber:"+cardNumber+"cardcvv:"+cardCVV+"cardvalid:"+cardValidity);

    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        agencyFragment = new CCAGENCYFragment();
        numberFragment = new CCNumberFragment();
        nameFragment = new CCNameFragment();
        validityFragment = new CCValidityFragment();
        secureCodeFragment = new CCSecureCodeFragment();
        adapter.addFragment(agencyFragment);
        adapter.addFragment(numberFragment);
        adapter.addFragment(nameFragment);
        adapter.addFragment(validityFragment);
        adapter.addFragment(secureCodeFragment);

        total_item = adapter.getCount() - 1;
        viewPager.setAdapter(adapter);

    }

    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }
        // Flip to the back.
        //setCustomAnimations(int enter, int exit, int popEnter, int popExit)

        mShowingBack = true;

        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.fragment_container, cardBackFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        int pos = viewPager.getCurrentItem();
        if (pos > 0) {
            viewPager.setCurrentItem(pos - 1);
        } else
            super.onBackPressed();
    }

    public void nextClick() {
        btnNext.performClick();
    }
}
