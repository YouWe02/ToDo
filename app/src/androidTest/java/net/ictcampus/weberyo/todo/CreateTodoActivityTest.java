package net.ictcampus.weberyo.todo;

import android.provider.MediaStore;
import android.support.test.filters.SmallTest;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.junit.Test;

public class CreateTodoActivityTest extends ActivityInstrumentationTestCase2<Create_Todo_Activity> {
    public CreateTodoActivityTest() {
        super(Create_Todo_Activity.class);
    }

    @Override
    protected void setUp() throws Exception{
        super.setUp();
    }

    @SmallTest
    public void test_allElementsCreated(){

        //Get all Elements of this Activity
        TextView textView2 = (TextView) getActivity().findViewById(R.id.textView2);
        EditText editText_title = (EditText) getActivity().findViewById(R.id.Title_Todo);
        TextView textView13 = (TextView) getActivity().findViewById(R.id.textView13);
        EditText editText_descprition = (EditText) getActivity().findViewById(R.id.Description_Todo);
        TextView textView3 = (TextView) getActivity().findViewById(R.id.textView3);
        LinearLayout linearLayout2 =  (LinearLayout) getActivity().findViewById(R.id.linearLayout2);
        NumberPicker picker_day = (NumberPicker) getActivity().findViewById(R.id.datepicker_day);
        NumberPicker picker_month = (NumberPicker) getActivity().findViewById(R.id.datepicker_month);
        NumberPicker picker_year = (NumberPicker) getActivity().findViewById(R.id.datepicker_year);
        RadioGroup allRadios = (RadioGroup) getActivity().findViewById(R.id.radiobutton_priority);
        RadioButton radio_1 = (RadioButton) getActivity().findViewById(R.id.radiobutton_priority_1);
        RadioButton radio_2 = (RadioButton) getActivity().findViewById(R.id.radiobutton_priority_2);
        RadioButton radio_3 = (RadioButton) getActivity().findViewById(R.id.radiobutton_priority_3);
        RadioButton radio_4 = (RadioButton) getActivity().findViewById(R.id.radiobutton_priority_4);
        RadioButton radio_5 = (RadioButton) getActivity().findViewById(R.id.radiobutton_priority_5);
        TextView textView11 = (TextView) getActivity().findViewById(R.id.textView11);
        Spinner spinner_category = (Spinner) getActivity().findViewById(R.id.spinner_category);
        TextView textView15 = (TextView) getActivity().findViewById(R.id.textView15);
        CheckBox privacy = (CheckBox) getActivity().findViewById(R.id.private_checkbox);
        Button submit = (Button) getActivity().findViewById(R.id.submit_todo);


        //Check, if all Elements are found
        assertNotNull(textView2);
        assertNotNull(editText_title);
        assertNotNull(textView13);
        assertNotNull(editText_descprition);
        assertNotNull(textView3);
        assertNotNull(linearLayout2);
        assertNotNull(picker_day);
        assertNotNull(picker_month);
        assertNotNull(picker_year);
        assertNotNull(allRadios);
        assertNotNull(radio_1);
        assertNotNull(radio_2);
        assertNotNull(radio_3);
        assertNotNull(radio_4);
        assertNotNull(radio_5);
        assertNotNull(textView11);
        assertNotNull(spinner_category);
        assertNotNull(textView15);
        assertNotNull(privacy);
        assertNotNull(submit);

    }

    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }
}
