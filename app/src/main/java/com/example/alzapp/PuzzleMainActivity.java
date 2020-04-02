package com.example.alzapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.alzapp.A_Star_Class_Solver.model.Board;
import com.example.alzapp.A_Star_Class_Solver.model.Place;

import java.io.FileNotFoundException;

//import com.example.alzapp.A_Star_Class_Solver.model.R;


public class PuzzleMainActivity extends AppCompatActivity {

    /** The main view. */
    private ViewGroup mainView;

    /** The game board. */
    private Board board;

    /** The board view that generates the tiles and lines using 2-D graphics. */
    private BoardView boardView;

    /** Text view to show the user the number of movements. */
    private TextView moves;

    /** The board size. Default value is an 4x4 game. */
    private int boardSize = 4;

    private Chronometer chronometer;
    public boolean running;

    Button b;

    /*
     * (non-Javadoc)
     *
     * @see android.support.v7.app.ActionBarActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_tile_puzzle);
        mainView = (ViewGroup) findViewById(R.id.mainLayout);
        chronometer = (Chronometer) findViewById(R.id.slidingTileChronometer);
        moves = (TextView) findViewById(R.id.moves);
        moves.setTextColor(Color.WHITE);
        moves.setTextSize(20);
        this.newGame();


        b = findViewById(R.id.btnSelectImage);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                photoPickerIntent.setType("image/*");
                photoPickerIntent.putExtra(Intent.EXTRA_LOCAL_ONLY, false);
                startActivityForResult(Intent.createChooser(photoPickerIntent,"Complete Action Using"), 1);
            }
        });


        if (!running) {
            chronometer.start();
            running = true;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri pickedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(pickedImage));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bitmap = resizeBitmapFitXY(this.boardView.getWidth(), this.boardView.getHeight(), bitmap);
            boardView.setBitmap(bitmap);
        }
    }

    public Bitmap resizeBitmapFitXY(int width, int height, Bitmap bitmap){
        Bitmap background = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        float originalWidth = bitmap.getWidth(), originalHeight = bitmap.getHeight();
        Canvas canvas = new Canvas(background);
        float scale, xTranslation = 0.0f, yTranslation = 0.0f;
        if (originalWidth > originalHeight) {
            scale = height/originalHeight;
            xTranslation = (width - originalWidth * scale)/2.0f;
        }
        else {
            scale = width / originalWidth;
            yTranslation = (height - originalHeight * scale)/2.0f;
        }
        Matrix transformation = new Matrix();
        transformation.postTranslate(xTranslation, yTranslation);
        transformation.preScale(scale, scale);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, transformation, paint);
        return background;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Generates a new game.
     */
    private void newGame() {
        this.board = new Board(this.boardSize);
        this.board.addBoardChangeListener(boardChangeListener);
        this.board.rearrange();
        this.mainView.removeView(boardView);
        this.boardView = new BoardView(this, board);
        this.mainView.addView(boardView);
        this.moves.setText("Number of movements: 0");
    }

    /**
     * Changes the size of the board
     *
     * @param newSize
     */
    public void changeSize(int newSize) {
        if (newSize != this.boardSize) {
            this.boardSize = newSize;
            this.newGame();
            boardView.invalidate();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                FragmentManager fm = getSupportFragmentManager();
                SettingsDialogFragment settings = new SettingsDialogFragment(
                        this.boardSize);
                settings.show(fm, "fragment_settings");
                break;
            case R.id.action_new_game:
                new AlertDialog.Builder(this)
                        .setTitle("New Game")
                        .setMessage("Are you sure you want to begin a new game?")
                        .setPositiveButton(android.R.string.yes,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        board.rearrange();
                                    }
                                })
                        .setNegativeButton(android.R.string.no,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // do nothing
                                    }
                                }).setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;
            case R.id.action_help:
                boardView.autoSolve();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /** The board change listener. */
    private Board.BoardChangeListener boardChangeListener = new Board.BoardChangeListener() {
        public void tileSlid(Place from, Place to, int numOfMoves) {
            moves.setText("Number of movements: "
                    + Integer.toString(numOfMoves));
        }

        public void solved(int numOfMoves) {
            moves.setText("Solved in " + Integer.toString(numOfMoves)
                    + " moves!");

            if(running){
                chronometer.stop();
                running = false;
            }

            Toast.makeText(getApplicationContext(), "You won!",
                    Toast.LENGTH_LONG).show();

                finish();
        }
    };

    /**
     * The Class SettingsDialogFragment. Shows the settings alert dialog in
     * order to change the size of the board.
     */
    public class SettingsDialogFragment extends DialogFragment {

        /** The size. */
        private int size;

        /**
         * Instantiates a new settings dialog fragment.
         *
         * @param size
         *            the size
         */
        public SettingsDialogFragment(int size) {
            this.size = size;
        }

        /**
         * Sets the size.
         *
         * @param size
         *            the new size
         */
        void setSize(int size) {
            this.size = size;
        }

        /**
         * Gets the size.
         *
         * @return the size
         */
        int getSize() {
            return this.size;
        }

        /*
         * (non-Javadoc)
         *
         * @see
         * android.support.v4.app.DialogFragment#onCreateDialog(android.os.Bundle
         * )
         */
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Set the dialog title
            builder.setTitle("Define the size of the puzzle")
                    .setSingleChoiceItems(R.array.size_options, this.size - 2,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    setSize(which + 2);

                                }

                            })
                    .setPositiveButton("Change",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    ((PuzzleMainActivity) getActivity())
                                            .changeSize(getSize());
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });

            return builder.create();
        }
    }
}