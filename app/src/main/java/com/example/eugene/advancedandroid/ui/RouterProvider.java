package com.example.eugene.advancedandroid.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

public interface RouterProvider {

    Router getRouter();

    Controller initialScreen();
}
