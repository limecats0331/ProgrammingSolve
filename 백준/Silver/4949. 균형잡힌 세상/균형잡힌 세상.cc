#include "bits/stdc++.h"

using namespace std;

int main() {
    ios::sync_with_stdio(false), cin.tie(nullptr);
    while (true) {
        stack<char> stack;
        string s;
        getline(cin, s);
        if (s == ".") break;
        for (char c: s) {
            if (c == '[' || c == '(') {
                stack.push(c);
            } else if (c == ']') {
                if (!stack.empty() && stack.top() == '[') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            } else if (c == ')') {
                if (!stack.empty() && stack.top() == '(') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        string result = stack.empty() ? "yes" : "no";
        cout << result << "\n";

    }


    return 0;
}