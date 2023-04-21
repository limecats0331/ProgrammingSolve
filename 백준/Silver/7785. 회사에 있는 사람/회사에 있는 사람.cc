#include "bits/stdc++.h"

using namespace std;

int N;
set<string> people;

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        string person;
        string state;
        cin >> person >> state;
        if (state == "enter") {
            people.insert(person);
        } else {
            people.erase(person);
        }
    }

    vector<string> names = vector<string>(people.begin(), people.end());
    sort(names.begin(), names.end(), greater<string>());
    for (int i = 0; i < names.size(); ++i) {
        cout << names[i] << "\n";
    }

    return 0;
}
