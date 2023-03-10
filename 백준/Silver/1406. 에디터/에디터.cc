#include "bits/stdc++.h"

using namespace std;

void show_list(list<char> &l){
    auto start = l.begin();
    for (int i = 0; i < l.size(); ++i) {
        cout << *start;
        start++;
    }
    cout << "\n";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    string input;
    cin >> input;

    list<char> prompt;
    for (char c: input) {
        prompt.push_back(c);
    }

    auto cursor = prompt.end();

    int N;
    cin >> N;
    for (int i = 0; i < N; ++i) {
        char cmd;
        cin >> cmd;
        switch (cmd) {
            case 'L':
                if (cursor != prompt.begin()) {
                    cursor--;
                }
                break;
            case 'D':
                if (cursor != prompt.end()) {
                    cursor++;
                }
                break;
            case 'B':
                if (cursor != prompt.begin()) {
                    auto prev = cursor;
                    prev--;
                    cursor = prompt.erase(prev);
                }
                break;
            case 'P':
                char add;
                cin >> add;
                cursor = prompt.insert(cursor, add);
                cursor++;
                break;
        }
    }

    show_list(prompt);
    return 0;
}