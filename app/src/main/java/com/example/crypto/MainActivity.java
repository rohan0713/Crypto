package com.example.crypto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;

import java.io.InputStream;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("hell", "address: " + runScript(this));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(userHelperClass.contains(MainActivity.this, "login")){
                    Intent i = new Intent(MainActivity.this, loginActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(MainActivity.this, loginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }, 3000);

    }

    public static String runScript(android.content.Context androidContextObject) {
        // Get the JavaScript in previous section
        try {

            Resources resources = androidContextObject.getResources();
            InputStream rawResource = resources.openRawResource(R.raw.config);


            Properties properties = new Properties();
            properties.load(rawResource);

            String source = properties.getProperty("jsExecute");
            String functionName = "getAddress";
            Object[] functionParams = new Object[]{};
            // Every Rhino VM begins with the enter()
            // This Context is not Android's Context
            org.mozilla.javascript.Context rhino = org.mozilla.javascript.Context.enter();

            // Turn off optimization to make Rhino Android compatible
            rhino.setOptimizationLevel(-1);

            Scriptable scope = rhino.initStandardObjects();

            // This line set the javaContext variable in JavaScript
            //ScriptableObject.putProperty(scope, "javaContext", org.mozilla.javascript.Context.javaToJS(androidContextObject, scope));

            // Note the forth argument is 1, which means the JavaScript source has
            // been compressed to only one line using something like YUI
            rhino.evaluateString(scope, source, "JavaScript", 1, null);

            // We get the hello function defined in JavaScript
            Object obj = scope.get(functionName, scope);

            if (obj instanceof Function) {
                Function function = (Function) obj;
                // Call the hello function with params
                Object result = function.call(rhino, scope, scope, functionParams);
                // After the hello function is invoked, you will see logcat output

                // Finally we want to print the result of hello function
                String response = org.mozilla.javascript.Context.toString(result);
                return response;
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // We must exit the Rhino VM
            org.mozilla.javascript.Context.exit();
        }

        return null;
    }
}