import 'package:auth_intern/constants.dart';
import 'package:auth_intern/features/screens/widgets/action_button.dart';
import 'package:auth_intern/features/screens/widgets/bottom_navigator.dart';
import 'package:auth_intern/features/screens/widgets/field_entry.dart';
import 'package:auth_intern/features/screens/widgets/scaffold_logo.dart';
import 'package:auth_intern/features/screens/widgets/scaffold_upper_part.dart';
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

class RegisterationScreen extends StatelessWidget {
  const RegisterationScreen({super.key});
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: <Widget>[
          SingleChildScrollView(
            physics: const NeverScrollableScrollPhysics(),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              mainAxisAlignment: MainAxisAlignment.start,
              children: <Widget>[
                const ScaffoldUpperPart(),
                SizedBox(
                  height: MediaQuery.of(context).size.height * 0.11,
                ),
                const Align(
                  child: Text(
                    'Create new account',
                    style: TextStyle(
                        color: kPrimaryColor,
                        fontSize: 20,
                        fontWeight: FontWeight.w700),
                  ),
                ),
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    FieldEntry(
                      label: 'Username',
                      icon: Icons.clear,
                      onPressed: () => null,
                      isPass: false,
                    ),
                    FieldEntry(
                      label: 'Email',
                      icon: Icons.clear,
                      onPressed: () => null,
                      isPass: false,
                    ),
                    FieldEntry(
                      label: 'Password',
                      icon: Icons.visibility_off,
                      onPressed: () => null,
                      isPass: true,
                    ),
                    Padding(
                      padding: const EdgeInsets.all(6.0),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Row(
                            children: [
                              Checkbox(value: false, onChanged: (value) {}),
                              const Text(
                                'Remember me',
                                style: TextStyle(
                                    color: Colors.black87,
                                    fontWeight: FontWeight.bold),
                              ),
                            ],
                          ),
                          TextButton(
                            onPressed: () {},
                            child: const Text(
                              'Have a problem?',
                              style: TextStyle(
                                color: Colors.black87,
                                fontWeight: FontWeight.w700,
                                decoration: TextDecoration.underline,
                                decorationColor: kSecondaryColor,
                                decorationThickness: 1.5,
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                    ActionButton(
                      color: kSecondaryColor,
                      label: 'Register',
                      onPressed: () {
                        context.go('/homeScreen');
                      },
                    ),
                    BottomNavigator(
                      question: 'Already have an account?',
                      action: 'Log in',
                      onPressed: () {
                        context.go('/');
                      },
                    )
                  ],
                ),
              ],
            ),
          ),
          const ScaffoldLogo()
        ],
      ),
    );
  }
}

