package com.slackow.boundlesswindow.mixin;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.GpuBackend;
import com.slackow.boundlesswindow.BoundlessWindow;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Window.class)
public class WindowMixin {
    @Inject(
            method = "createGlfwWindow",
            at = @At(value = "INVOKE", remap = false, target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J")
    )
    private static void addHints(int width, int height, String title, long monitor, GpuBackend backend, CallbackInfoReturnable<Long> ci) {
        if (BoundlessWindow.config != null && BoundlessWindow.config.removeTitlebar()) {
            GLFW.glfwWindowHint(GLFW.GLFW_DECORATED, GLFW.GLFW_FALSE);
            GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
        }
    }
}
