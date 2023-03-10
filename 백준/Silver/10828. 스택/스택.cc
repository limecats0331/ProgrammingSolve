#include "bits/stdc++.h"

using namespace std;

int main() {
    ios::sync_with_stdio(false), cin.tie(nullptr);
    stack<int> stack1;

    int N;
    cin >> N;
    for (int i = 0; i < N; ++i) {
        string cmd;
        cin >> cmd;

        if (cmd == "push") {
            int num;
            cin >> num;
            stack1.push(num);
        } else if (cmd == "pop") {
            if (stack1.empty()) {
                cout << -1 << "\n";
            } else {
                cout << stack1.top() << "\n";
                stack1.pop();
            }
        } else if (cmd == "size") {
            cout << stack1.size() << "\n";
        } else if (cmd == "empty") {
            if (stack1.empty()) {
                cout << 1 << "\n";
            } else {
                cout << 0 << "\n";
            }
        } else if (cmd == "top") {
            if (stack1.empty()) {
                cout << -1 << "\n";
            } else {
                cout << stack1.top() << "\n";
            }
        }
    }

    return 0;
}